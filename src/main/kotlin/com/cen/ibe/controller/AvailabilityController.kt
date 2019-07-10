package com.cen.ibe.controller

import com.cen.ibe.dto.request.AvailabilityRequestDTO
import com.cen.ibe.dto.response.AvailabilityResponseDTO
import com.cen.ibe.service.AvailabilityService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/availability")
class AvailabilityController @Autowired constructor(
        val availabilityService: AvailabilityService
) {

    val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun availability(@RequestBody body: AvailabilityRequestDTO): ResponseEntity<AvailabilityResponseDTO> {
        logger.info("Checking availability for: {}", body)

        if (!body.validate())
            return ResponseEntity.badRequest().build()

        return ResponseEntity.ok(availabilityService.checkAvailability(body))
    }
}