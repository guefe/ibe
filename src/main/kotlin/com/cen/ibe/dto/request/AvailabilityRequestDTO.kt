package com.cen.ibe.dto.request

import com.cen.ibe.dto.OccupancyDTO
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.time.LocalDate

@JsonIgnoreProperties(ignoreUnknown = true)
data class AvailabilityRequestDTO(
        val startDate: LocalDate,
        val endDate: LocalDate,
        val occupancy: List<OccupancyDTO> ?
)