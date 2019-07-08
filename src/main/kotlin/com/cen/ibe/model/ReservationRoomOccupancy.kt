package com.cen.ibe.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity
class ReservationRoomOccupancy(

        @Column
        var adults: Int,

        @Column
        var juniors: Int,

        @Column
        var babies: Int,

        @ManyToOne(optional = false)
        var reservationRoom: ReservationRoom


) : BaseEntity() {

        override fun toString(): String {
            return "ReservationRoomOccupancy(adults=$adults, juniors=$juniors, babies=$babies, reservationRoom=${reservationRoom.id})"
        }
}