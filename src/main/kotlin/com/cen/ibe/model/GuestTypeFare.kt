package com.cen.ibe.model

import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity
class GuestTypeFare(

        @Column(precision = 6, scale = 2)
        var price: BigDecimal,

        @Column
        var startDate: LocalDate,

        @Column
        var endDate: LocalDate,

        @ManyToOne(optional = false)
        var guestType: GuestType


) : BaseEntity() {
        override fun toString(): String {
                return "GuestTypeFare(price=$price, startDate=$startDate, endDate=$endDate, guestType=$guestType)"
        }
}