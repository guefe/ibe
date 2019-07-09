package com.cen.ibe.dto

import com.cen.ibe.model.ReservationRoom
import com.cen.ibe.model.RoomType
import com.cen.ibe.model.StandardOccupancy
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty

data class RoomTypeDTO(val roomTypeCode: String, val roomTypeName: String, val occupancy: OccupancyDTO) {
    constructor(roomType: RoomType, occupancy: OccupancyDTO) :
            this(roomType.code, roomType.name, occupancy)

    constructor(reservationRoom: ReservationRoom) :
            this(reservationRoom.roomType,
                    OccupancyDTO(reservationRoom.adults, reservationRoom.juniors, reservationRoom.babies))
}

data class OccupancyDTO(@JsonProperty("adults") val adults: Int, @JsonProperty("juniors") val juniors: Int, @JsonProperty("babies") val babies: Int) {
    constructor (standardOccupancy: StandardOccupancy) :
            this(standardOccupancy.adults, standardOccupancy.juniors, standardOccupancy.babies)

    @get:JsonIgnore
    val total: Int
        get() = adults + juniors + babies
}