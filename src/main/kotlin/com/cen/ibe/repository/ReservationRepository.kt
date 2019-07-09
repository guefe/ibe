package com.cen.ibe.repository

import com.cen.ibe.model.Reservation
import org.springframework.data.repository.CrudRepository


interface ReservationRepository : CrudRepository<Reservation, Int> {
    fun findByReference(reference: String): Reservation
}