package com.cen.ibe.repository

import com.cen.ibe.model.GuestTypeFare
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.time.LocalDate

interface GuestTypeFareRepository : CrudRepository<GuestTypeFare, Long> {
    @Query("select gtf from GuestTypeFare gtf where gtf.guestType.id = ?1 and ((?2 between gtf.startDate and gtf.endDate) or (?3 between gtf.startDate and gtf.endDate))")
    fun findPriceForDates(guestTypeId: Int, startDate: LocalDate, endDate: LocalDate): List<GuestTypeFare>
}