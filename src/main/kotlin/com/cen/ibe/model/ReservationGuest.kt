package com.cen.ibe.model

import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity
class ReservationGuest(

        @Column
        var guestIdNumber: String,

        @Column(precision = 6, scale = 2)
        var amount: BigDecimal,

        @ManyToOne(optional = false)
        var guestType: GuestType,

        @ManyToOne(optional = false)
        var reservationRoom: ReservationRoom


) : BaseEntity() {
        override fun toString(): String {
                return "ReservationGuest(guestIdNumber='$guestIdNumber', amount=$amount, guestType=$guestType, reservationRoom=$reservationRoom)"
        }
}