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


    fun checkAvailability(availabilityRequestDTO: AvailabilityRequestDTO): AvailabilityResponseDTO? {
        logger.info("$availabilityRequestDTO")
        val result = AvailabilityResponseDTO(availabilityRequestDTO.startDate, availabilityRequestDTO.endDate)
        val availableRooms = roomService.findAvailableRooms(availabilityRequestDTO.startDate, availabilityRequestDTO.endDate)
        val roomTypes = matchRoomWithOccupancy(availableRooms, availabilityRequestDTO.occupancy)
        result.roomTypes = roomTypes
        return result
    }

    private fun matchRoomWithOccupancy(availableRooms: MutableList<RoomType>, occupancyList: List<OccupancyDTO>?)
            : MutableList<AvailabilityRoomTypeDTO> {
        val matchingRooms = mutableListOf<AvailabilityRoomTypeDTO>()
        occupancyList?.forEach { occupancy ->
            val room = availableRooms.find { room -> room.capacity >= occupancy.total }
            room?.let { matchingRooms.add(AvailabilityRoomTypeDTO(it, occupancy, room.quantity)) }
        } ?: availableRooms.forEach { matchingRooms.add(buildResponseRoomFromStandardOccupancy(it)) }

        return matchingRooms
    }

    private fun buildResponseRoomFromStandardOccupancy(roomType: RoomType) =
            AvailabilityRoomTypeDTO(roomType, OccupancyDTO(roomType.standardOccupancy), roomType.quantity)
}