package com.cen.ibe.repository

import com.cen.ibe.model.AvailabilityRestriction
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import java.time.LocalDate


interface AvailabilityRestrictionRepository : CrudRepository<AvailabilityRestriction, Int> {
    @Query("from AvailabilityRestriction where startDate<=:start_date and endDate>:start_date and type='CHECK_IN_DATE'")
    fun findForCheckInDate(@Param("start_date") startDate: LocalDate): AvailabilityRestriction?

    @Query("from AvailabilityRestriction where startDate<=:end_date and endDate>:end_date and type='CHECK_OUT_DATE'")
    fun findForCheckOutDate(@Param("end_date") endDate: LocalDate): AvailabilityRestriction?
}