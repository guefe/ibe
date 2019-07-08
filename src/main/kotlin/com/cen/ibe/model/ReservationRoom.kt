package com.cen.ibe.model

import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity
class ReservationRoom(

        @Column(precision = 8, scale = 2)
        var amount: BigDecimal,

        @ManyToOne(optional = false)
        var reservation: Reservation,

        @ManyToOne
        var room: Room



) : BaseEntity() {
        override fun toString(): String {
                return "ReservationRoom(amount=$amount, reservation=$reservation, room=$room)"
        }
}
