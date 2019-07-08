package com.cen.ibe.dto.response

import com.cen.ibe.dto.OccupancyDTO
import com.cen.ibe.model.RoomType
import com.fasterxml.jackson.annotation.JsonInclude
import java.math.BigDecimal
import java.time.LocalDate

data class AvailabilityResponseDTO(val startDate: LocalDate, val endDate: LocalDate) {
    var roomTypes: MutableList<AvailabilityRoomTypeDTO> = mutableListOf()
}

data class AvailabilityRoomTypeDTO(
        val roomTypeCode: String,
        val roomTypeName: String,
        val occupancy: AvailabilityOccupancyDTO,
        val amount: BigDecimal,
        val roomsAvailable: Int
) {
    constructor(roomType: RoomType, occupancy: OccupancyDTO, availability: Int) :
            this(
                    roomType.code,
                    roomType.name,
                    AvailabilityOccupancyDTO(occupancy.adults, occupancy.juniors, occupancy.babies),
                    roomType.adultPrice.multiply(occupancy.adults.toBigDecimal()) +
                            roomType.juniorPrice.multiply(occupancy.juniors.toBigDecimal()) +
                            roomType.babyPrice.multiply(occupancy.babies.toBigDecimal()),
                    availability
            )
}

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
data class AvailabilityOccupancyDTO(val adults: Int, val juniors: Int, val babies: Int)