package com.cen.ibe.repository

import com.cen.ibe.model.Reservation
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ReservationRepository : CrudRepository<Reservation, Long>