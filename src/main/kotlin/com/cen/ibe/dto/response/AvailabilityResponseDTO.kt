package com.cen.ibe.dto.response

import com.cen.ibe.dto.RoomTypeDTO
import java.time.LocalDate

data class AvailabilityResponseDTO (val startDate: LocalDate, val endDate: LocalDate) {
    val roomTypes: MutableList<RoomTypeDTO> = mutableListOf()
}