package com.cen.ibe.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.OneToOne

@Entity
class StandardOccupancy(

        @OneToOne
        var roomType: RoomType,

        @Column
        var adults: Int,

        @Column
        var juniors: Int,

        @Column
        var babies: Int


) : BaseEntity() {
        val total: Int
                get() = adults + juniors + babies

        override fun toString(): String {
                return "StandardOccupancy(roomType=${roomType.code}, adults=$adults, juniors=$juniors, babies=$babies)"
        }
}