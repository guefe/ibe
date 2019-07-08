package com.cen.ibe.model

import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.OneToOne

@Entity
class RoomType(

        @Column(nullable = false)
        var name: String,

        @Column(nullable = false, columnDefinition = "bpchar")
        var code: String,

        @Column(nullable = false)
        var capacity: Int,

        @Column(nullable = false)
        var quantity: Int,

        @Column
        var adultPrice: BigDecimal,

        @Column
        var juniorPrice: BigDecimal,

        @Column
        var babyPrice: BigDecimal,

        @OneToOne(mappedBy = "roomType", fetch = FetchType.LAZY)
        var standardOccupancy: StandardOccupancy

) : BaseEntity() {
        override fun toString(): String {
                return "RoomType(name='$name', code='$code', capacity=$capacity, adultPrice=$adultPrice, juniorPrice=$juniorPrice, babyPrice=$babyPrice, standardOccupancy=$standardOccupancy)"
        }
}