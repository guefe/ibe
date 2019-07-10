package com.cen.ibe.model

import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
class AvailabilityRestriction(

        @Column
        @Enumerated(EnumType.STRING)
        var type: RestrictionType,

        @Column
        var startDate: LocalDate,

        @Column
        var endDate: LocalDate,

        @Column
        var dayOfWeek: Int,

        @Column
        var minDays: Int?

) : BaseEntity()

enum class RestrictionType {
    CHECK_IN_DATE, CHECK_OUT_DATE
}