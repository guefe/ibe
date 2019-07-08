package com.cen.ibe.service

import com.cen.ibe.dto.GuestTypeDTO
import com.cen.ibe.dto.OccupancyDTO
import com.cen.ibe.dto.RoomTypeDTO
import com.cen.ibe.dto.request.AvailabilityRequestDTO
import com.cen.ibe.dto.response.AvailabilityResponseDTO
import com.cen.ibe.model.PreferredRoomConf
import com.cen.ibe.model.Room
import com.cen.ibe.repository.ReservationRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AvailabilityService @Autowired constructor(
        private val roomService: RoomService,
        private val guestTypeService: GuestTypeService,
        private val reservationRepository: ReservationRepository
) {

    private val logger = LoggerFactory.getLogger(this::class.java)!!


    fun checkAvailability(availabilityRequestDTO: AvailabilityRequestDTO): AvailabilityResponseDTO? {
        logger.info("$availabilityRequestDTO")
        var result = AvailabilityResponseDTO(availabilityRequestDTO.startDate, availabilityRequestDTO.endDate)
        var availableRooms = roomService.findAvailableRooms(availabilityRequestDTO.startDate, availabilityRequestDTO.endDate)


        var roomsRequest = availabilityRequestDTO.occupancy?.let { transformOccupancy(it) }
        guestTypeService.calculatePriceForRoom(availabilityRequestDTO.startDate, availabilityRequestDTO.endDate, guests)

        return null
    }

    private fun transformOccupancy(occupancy: List<OccupancyDTO>): List<List<GuestTypeDTO>> {
        var room = mutableListOf<GuestTypeDTO>()
        var roomsRequest = mutableListOf<List<GuestTypeDTO>>()
        occupancy.forEach {
            repeat(it.adults) { room.add(GuestTypeDTO("adult")) }
            repeat(it.juniors) { room.add(GuestTypeDTO("junior")) }
            repeat(it.babies) { room.add(GuestTypeDTO("baby")) }
            roomsRequest.add(room)
        }
        return roomsRequest
    }

    private fun buildRoomTypesResponse(availableRooms: MutableList<Room>, occupancy: List<OccupancyDTO>?, preferredRoomConf: List<PreferredRoomConf>): List<RoomTypeDTO> {
        var roomTypes = mutableListOf<RoomTypeDTO>()
        occupancy?.map { occupancyDTO ->
            val matchingRoom = availableRooms.first { room -> room.roomType.capacity >= occupancyDTO.adults + occupancyDTO.juniors }
            availableRooms.remove(matchingRoom)
            roomTypes.add(RoomTypeDTO(matchingRoom.roomType.code, matchingRoom.roomType.name, occupancyDTO))
        }?.let { return roomTypes }

        availableRooms.map { room ->
            var occupancy = OccupancyDTO(
                    room.roomType.preferredRoomConf
                            .filter { conf -> conf.guestType.name == "adult" }
                            .map { preferredRoomConf -> preferredRoomConf.quantity }
                            .sum(),
                    room.roomType.preferredRoomConf
                            .filter { conf -> conf.guestType.name == "junior" }
                            .map { preferredRoomConf -> preferredRoomConf.quantity }
                            .sum(),
                    room.roomType.preferredRoomConf
                            .filter { conf -> conf.guestType.name == "baby" }
                            .map { preferredRoomConf -> preferredRoomConf.quantity }
                            .sum()
            )
            roomTypes.add(RoomTypeDTO(room.roomType.code, room.roomType.name, occupancy))
        }
        return roomTypes

    }

}