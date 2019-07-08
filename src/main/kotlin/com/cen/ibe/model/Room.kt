package com.cen.ibe.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity
data class Room(
        @Column
        var roomNumber: Int,

        @ManyToOne
        var roomType: RoomType

) : BaseEntity() {
        override fun toString(): String {
                return "Room(roomNumber=$roomNumber, roomType=$roomType)"
        }
}