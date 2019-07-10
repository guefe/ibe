package com.cen.ibe.service

import com.cen.ibe.dto.OccupancyDTO
import com.cen.ibe.dto.request.AvailabilityRequestDTO
import com.cen.ibe.dto.response.AvailabilityResponseDTO
import com.cen.ibe.dto.response.AvailabilityRoomTypeDTO
import com.cen.ibe.model.RoomType
import com.cen.ibe.repository.AvailabilityRestrictionRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.temporal.ChronoUnit

@Service
class AvailabilityService @Autowired constructor(
        private val roomService: RoomService,
        private val availabilityRestrictionRepository: AvailabilityRestrictionRepository
) {

    private val logger = LoggerFactory.getLogger(this::class.java)!!


    /**
     * Retrieves available rooms for the dates and matches those rooms with the requested occupancy
     * Also checks restrictions
     */
    fun checkAvailability(availabilityRequestDTO: AvailabilityRequestDTO): AvailabilityResponseDTO {
        logger.info("$availabilityRequestDTO")
        val result = AvailabilityResponseDTO(availabilityRequestDTO.startDate, availabilityRequestDTO.endDate)

        if (checkAvailabilityRestrictions(availabilityRequestDTO)) {
            val availableRooms =
                    roomService.findAvailableRooms(availabilityRequestDTO.startDate, availabilityRequestDTO.endDate)
            val roomTypes =
                    matchRoomWithOccupancy(availableRooms, availabilityRequestDTO.occupancy)
            result.roomTypes = roomTypes
        }

        return result
    }

    private fun checkAvailabilityRestrictions(availabilityRequestDTO: AvailabilityRequestDTO): Boolean {
        return checkInAllowed(availabilityRequestDTO) && checkOutAllowed(availabilityRequestDTO)
    }

    private fun checkInAllowed(availabilityRequestDTO: AvailabilityRequestDTO): Boolean {
        val restriction =
                availabilityRestrictionRepository.findForCheckInDate(availabilityRequestDTO.startDate)
        restriction?.let { value ->
            val requestedDays =
                    ChronoUnit.DAYS.between(availabilityRequestDTO.startDate, availabilityRequestDTO.endDate)
            return availabilityRequestDTO.startDate.dayOfWeek.value != value.dayOfWeek
                    || (value.minDays?.compareTo(requestedDays)?.let { it <= 0 } ?: true)
        } ?: return true
    }

    private fun checkOutAllowed(availabilityRequestDTO: AvailabilityRequestDTO): Boolean {
        val restriction =
                availabilityRestrictionRepository.findForCheckOutDate(availabilityRequestDTO.endDate)
        restriction?.let {
            return availabilityRequestDTO.endDate.dayOfWeek.value != it.dayOfWeek
        } ?: return true
    }

    private fun matchRoomWithOccupancy(availableRooms: MutableList<RoomType>, occupancyList: List<OccupancyDTO>?)
            : List<AvailabilityRoomTypeDTO> {
        val matchingRooms = mutableSetOf<AvailabilityRoomTypeDTO>()

        // when the request contains a given occupancy, match the available rooms with that occupancy
        if (occupancyList != null) {
            // Counts occupancies to match it with the availability
            val occupancyMap = occupancyList.groupingBy { it }.eachCount()
            occupancyMap.forEach { (occupancy, requestedQuantity) ->
                val roomCandidates = availableRooms.filter { it.capacity >= occupancy.total }
                if (roomCandidates.sumBy { it.quantity } >= requestedQuantity) {
                    roomCandidates.forEach { room ->
                        run {
                            val matchingRoom = AvailabilityRoomTypeDTO(room, occupancy, room.quantity)
                            if (!matchingRooms.any { e -> e.roomTypeCode == matchingRoom.roomTypeCode })
                                matchingRooms.add(matchingRoom)
                        }
                    }
                } else {
                    // no matching room for current occupancy = no availability
                    return mutableListOf()
                }
            }

            return if (matchingRooms.toList().sumBy { it.roomsAvailable } >= occupancyList.size)
                matchingRooms.toMutableList()
            else
                mutableListOf()

        } else {
            availableRooms.forEach { matchingRooms.add(buildResponseRoomFromStandardOccupancy(it)) }
            return matchingRooms.toMutableList()
        }

    }

    private fun buildResponseRoomFromStandardOccupancy(roomType: RoomType) =
            AvailabilityRoomTypeDTO(roomType, OccupancyDTO(roomType.standardOccupancy), roomType.quantity)
}