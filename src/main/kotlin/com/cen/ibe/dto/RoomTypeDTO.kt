package com.cen.ibe.dto

data class RoomTypeDTO(val roomTypeCode: String, val roomTypeName: String, val occupancy: OccupancyDTO)

data class OccupancyDTO(val adults: Int, val juniors: Int, val babies: Int)