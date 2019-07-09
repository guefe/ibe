package com.cen.ibe.model

import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.*

@Entity
class Reservation(

        @Column
        var reference: String,

        @Column
        var totalAmount: BigDecimal,

        @Column
        var customerFullName: String,

        @Column
        var customerEmail: String,

        @Column
        var startDate: LocalDate,

        @Column
        var endDate: LocalDate,

        @OneToMany(mappedBy = "reservation")
        var rooms: MutableList<ReservationRoom>


) {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null

        override fun toString(): String {
                return "Reservation(reference='$reference', totalAmount=$totalAmount, customerFullName='$customerFullName', customerEmail='$customerEmail', startDate=$startDate, endDate=$endDate, rooms=$rooms)"
        }
}