package com.cen.ibe.service

import com.cen.ibe.dto.OccupancyDTO
import com.cen.ibe.dto.request.AvailabilityRequestDTO
import com.cen.ibe.dto.response.AvailabilityResponseDTO
import com.cen.ibe.dto.response.AvailabilityRoomTypeDTO
import com.cen.ibe.model.RoomType
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AvailabilityService @Autowired constructor(
        private val roomService: RoomService
) {

    private val logger = LoggerFactory.getLogger(this::class.java)!!


    fun checkAvailability(availabilityRequestDTO: AvailabilityRequestDTO): AvailabilityResponseDTO {
        logger.info("$availabilityRequestDTO")
        val result = AvailabilityResponseDTO(availabilityRequestDTO.startDate, availabilityRequestDTO.endDate)
        val availableRooms =
                roomService.findAvailableRooms(availabilityRequestDTO.startDate, availabilityRequestDTO.endDate)
        val roomTypes =
                matchRoomWithOccupancy(availableRooms, availabilityRequestDTO.occupancy)
        result.roomTypes = roomTypes
        return result
    }

    private fun matchRoomWithOccupancy(availableRooms: MutableList<RoomType>, occupancyList: List<OccupancyDTO>?)
            : MutableList<AvailabilityRoomTypeDTO> {
        val matchingRooms = mutableSetOf<AvailabilityRoomTypeDTO>()

        if (occupancyList != null) {
            val occupancyMap = occupancyList.groupingBy { it }.eachCount()

            occupancyMap.forEach { (occupancy, quantity) ->
                val roomCandidates = availableRooms.filter { room -> room.capacity >= occupancy.total }
                if (roomCandidates.sumBy { it.quantity } >= quantity) {
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