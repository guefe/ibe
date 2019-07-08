package com.cen.ibe.service

import com.cen.ibe.model.RoomType
import com.cen.ibe.repository.RoomTypeRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class RoomService @Autowired constructor(
        private val roomTypeRepository: RoomTypeRepository

) {
    private val logger = LoggerFactory.getLogger(this::class.java)!!


    fun findAvailableRooms(startDate: LocalDate, endDate: LocalDate): MutableList<RoomType> {
        val availableRooms = roomTypeRepository.findAvailableRoomsByDates(startDate, endDate)
        logger.info("Available rooms for $startDate - $endDate: ")
        availableRooms.forEach { logger.info("$it") }
        return availableRooms
    }
}