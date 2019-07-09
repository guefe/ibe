package com.cen.ibe.model

import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity
class ReservationRoom(

        @Column(precision = 8, scale = 2)
        var amount: BigDecimal,

        @Column
        var adults: Int,

        @Column
        var juniors: Int,

        @Column
        var babies: Int,

        @ManyToOne
        var roomType: RoomType,

        @ManyToOne(optional = false)
        var reservation: Reservation? = null

) : BaseEntity() {
        override fun toString(): String {
                return "ReservationRoom(amount=$amount, adults=$adults, juniors=$juniors, babies=$babies, " +
                        "reservation=${reservation?.id}, roomType=${roomType.code})"
        }
}
