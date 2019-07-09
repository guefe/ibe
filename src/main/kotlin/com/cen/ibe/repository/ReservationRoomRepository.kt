package com.cen.ibe.repository

import com.cen.ibe.model.ReservationRoom
import org.springframework.data.repository.CrudRepository


interface ReservationRoomRepository : CrudRepository<ReservationRoom, Int>