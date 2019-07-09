package com.cen.ibe.dto.response

import com.cen.ibe.config.MoneySerializer
import com.cen.ibe.dto.OccupancyDTO
import com.cen.ibe.model.Reservation
import com.cen.ibe.model.ReservationRoom
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import java.math.BigDecimal
import java.time.LocalDate

data class ReservationResponseDTO(
        val reference: String,
        val startDate: LocalDate,
        val endDate: LocalDate,
        @JsonSerialize(using = MoneySerializer::class)
        val totalAmount: BigDecimal,
        val customerFullName: String,
        val customerEmail: String,
        val roomsTypes: List<ReservationRoomTypeDTO>

) {
    constructor(reservation: Reservation) :
            this(reservation.reference,
                    reservation.startDate,
                    reservation.endDate,
                    reservation.totalAmount,
                    reservation.customerFullName,
                    reservation.customerEmail,
                    reservation.rooms.map { ReservationRoomTypeDTO(it) })


}

data class ReservationRoomTypeDTO(
        val roomTypeCode: String,
        val roomTypeName: String,
        val occupancy: OccupancyDTO,
        @JsonSerialize(using = MoneySerializer::class)
        val amount: BigDecimal) {
    constructor(reservationRoom: ReservationRoom) :
            this(reservationRoom.roomType.code, reservationRoom.roomType.name,
                    OccupancyDTO(reservationRoom.adults, reservationRoom.juniors, reservationRoom.babies), reservationRoom.amount)
}