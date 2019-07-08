package com.cen.ibe.dto.request

import com.cen.ibe.dto.RoomTypeDTO
import java.time.LocalDate

data class ReservationRequestDTO(
        val startDate: LocalDate,
        val endDate: LocalDate,
        val customerMail: String,
        val customerFullName: String,
        val roomTypes: List<RoomTypeDTO>
)

