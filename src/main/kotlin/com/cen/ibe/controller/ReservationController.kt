package com.cen.ibe.controller

import com.cen.ibe.dto.request.ReservationRequestDTO
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/reservation")
class ReservationController {

    val logger = LoggerFactory.getLogger(this::class.java)!!

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun makeReservation(@RequestBody body: ReservationRequestDTO): String {
        logger.info("body {}", body)
        return "Hello boy"
    }
}