package com.cen.ibe.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity
class PreferredRoomConf(

        @ManyToOne
        var roomType: RoomType,

        @ManyToOne
        var guestType: GuestType,

        @Column
        var quantity: Int


) : BaseEntity() {
        override fun toString(): String {
                return "PreferredRoomConf(roomType=${roomType.code}, guestType=${guestType.name}, quantity=$quantity)"
        }
}