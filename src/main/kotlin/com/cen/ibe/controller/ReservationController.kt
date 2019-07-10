package com.cen.ibe.controller

import com.cen.ibe.dto.request.ReservationFilterDTO
import com.cen.ibe.dto.request.ReservationRequestDTO
import com.cen.ibe.dto.response.ReservationResponseDTO
import com.cen.ibe.service.ReservationService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/reservation")
class ReservationController @Autowired constructor(
        private val reservationService: ReservationService
) {

    val logger = LoggerFactory.getLogger(this::class.java)!!

    @PostMapping("verify_reservation")
    fun findReservation(@RequestBody filter: ReservationFilterDTO): ReservationResponseDTO {
        logger.info("Finding reservation: $filter")
        return reservationService.findReservation(filter)
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun makeReservation(@RequestBody request: ReservationRequestDTO): ResponseEntity<ReservationResponseDTO> {
        logger.info("New reservation requested: $request")
        if (!request.validate())
            return ResponseEntity.badRequest().build()
        return ResponseEntity.ok(reservationService.makeReservation(request))
    }
}