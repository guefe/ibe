package com.cen.ibe.dto

import com.cen.ibe.model.RoomType
import com.cen.ibe.model.StandardOccupancy

data class RoomTypeDTO(val roomTypeCode: String, val roomTypeName: String, val occupancy: OccupancyDTO) {
    constructor(roomType: RoomType, occupancy: OccupancyDTO) :
            this(roomType.code, roomType.name, occupancy)
}

data class OccupancyDTO(val adults: Int, val juniors: Int, val babies: Int) {
    constructor (standardOccupancy: StandardOccupancy) :
            this(standardOccupancy.adults, standardOccupancy.juniors, standardOccupancy.babies)

    val total: Int
        get() = adults + juniors + babies
}