package com.cen.ibe.service

import com.cen.ibe.dto.request.AvailabilityRequestDTO
import com.cen.ibe.dto.request.ReservationFilterDTO
import com.cen.ibe.dto.request.ReservationRequestDTO
import com.cen.ibe.dto.request.RoomTypeRequestDTO
import com.cen.ibe.dto.response.AvailabilityRoomTypeDTO
import com.cen.ibe.dto.response.ReservationResponseDTO
import com.cen.ibe.model.Reservation
import com.cen.ibe.model.ReservationRoom
import com.cen.ibe.repository.ReservationRepository
import com.cen.ibe.repository.ReservationRoomRepository
import com.cen.ibe.repository.RoomTypeRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional
import kotlin.streams.asSequence

@Service
@Transactional
class ReservationService @Autowired constructor(
        private val availabilityService: AvailabilityService,
        private val reservationRepository: ReservationRepository,
        private val reservationRoomRepository: ReservationRoomRepository,
        private val roomTypeRepository: RoomTypeRepository
) {
    val logger = LoggerFactory.getLogger(this::class.java)!!


    fun findReservation(filter: ReservationFilterDTO): ReservationResponseDTO {
        return ReservationResponseDTO(reservationRepository.findByReference(filter.reference))
    }

    fun makeReservation(request: ReservationRequestDTO): ReservationResponseDTO {
        val availabilityRequest = AvailabilityRequestDTO(
                request.startDate,
                request.endDate,
                request.roomTypes.map { it.occupancy })
        val availabilityResponse = availabilityService.checkAvailability(availabilityRequest)
        if (availabilityResponse.roomTypes.isNotEmpty()) {
            var reservationRooms = mutableListOf<ReservationRoom>()
            request.roomTypes.forEach { roomRequest ->
                run {
                    val availableRoomType =
                            availabilityResponse.roomTypes.find { it.roomTypeCode == roomRequest.roomTypeCode }

                    availableRoomType?.let {
                        reservationRooms.add(createReservationRoom(it, roomRequest))
                        val reservation = createReservation(request, reservationRooms)
                        reservationRepository.save(reservation)
                        reservationRoomRepository.saveAll(reservationRooms)
                        return ReservationResponseDTO(reservation)
                    }

                }
            }

        }
        logger.error("No available")
        throw Exception()
    }

    private fun createReservation(reservationRequest: ReservationRequestDTO,
                                  reservationRooms: MutableList<ReservationRoom>): Reservation {
        val totalAmount = reservationRooms.map { it.amount }.reduce { acc, elem -> acc.add(elem) }
        var reservation = Reservation(generateReference(), totalAmount, reservationRequest.customerFullName,
                reservationRequest.customerMail, reservationRequest.startDate, reservationRequest.endDate,
                reservationRooms)
        reservationRooms.forEach { it.reservation = reservation }
        return reservation

    }

    private fun createReservationRoom(availabilityRoomTypeDTO: AvailabilityRoomTypeDTO, roomRequest: RoomTypeRequestDTO): ReservationRoom {
        return ReservationRoom(availabilityRoomTypeDTO.amount,
                roomRequest.occupancy.adults,
                roomRequest.occupancy.juniors,
                roomRequest.occupancy.babies,
                roomTypeRepository.findByCode(availabilityRoomTypeDTO.roomTypeCode))
    }

    private fun generateReference(): String {
        val source = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"
        return java.util.Random().ints(6, 0, source.length)
                .asSequence()
                .map(source::get)
                .joinToString("")
    }
}