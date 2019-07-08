package com.cen.ibe.service

import com.cen.ibe.model.GuestType
import com.cen.ibe.model.GuestTypeFare
import com.cen.ibe.repository.GuestTypeFareRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDate

@Service
class GuestTypeService @Autowired constructor(
        private val guestTypeFareRepository: GuestTypeFareRepository
) {
    private val logger = LoggerFactory.getLogger(this::class.java)!!

    fun calculatePriceForRoom(startDate: LocalDate, endDate: LocalDate, guestTypes: List<GuestType>): BigDecimal {
        var roomPrice = BigDecimal.ZERO
        guestTypes.forEach {
            roomPrice += guestTypeFareRepository.findPriceForDates(it.id!!, startDate, endDate)
                    .map(GuestTypeFare::price)
                    .reduce { sum, price -> sum + price }

        }
        return roomPrice
    }
}