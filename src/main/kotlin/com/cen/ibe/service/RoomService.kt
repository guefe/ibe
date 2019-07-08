package com.cen.ibe.service

import com.cen.ibe.model.Room
import com.cen.ibe.repository.RoomRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class RoomService @Autowired constructor(
        private val roomRepository: RoomRepository,
        private val guestTypeService: GuestTypeService

) {
    private val logger = LoggerFactory.getLogger(this::class.java)!!


    fun findAvailableRooms(startDate: LocalDate, endDate: LocalDate): MutableList<Room> {
        val availableRooms = roomRepository.findAvailableRoomsByDates(startDate, endDate)
        logger.info("Available rooms for $startDate - $endDate: ")
        availableRooms.forEach { logger.info("$it") }
        //val roomPrice = guestTypeService.calculatePriceForRoom(startDate, endDate, )
        //logger.info("$roomPrice")
        return availableRooms
    }
}