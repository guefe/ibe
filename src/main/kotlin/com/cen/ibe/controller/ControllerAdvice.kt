package com.cen.ibe.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.Instant

@ControllerAdvice
class ControllerAdvice : ResponseEntityExceptionHandler() {

    @ExceptionHandler(ReservationNoAvailabilityException::class)
    fun handleException(
            exception: ReservationNoAvailabilityException,
            request: WebRequest
    ): ResponseEntity<ErrorDetails> {
        return ResponseEntity
                .badRequest()
                .body(ErrorDetails(Instant.now().toEpochMilli(), exception.message, "IBE-001"))
    }
}

class ReservationNoAvailabilityException(
        override val message: String = "Unable to create reservation: No availability."
) : Exception(message)


data class ErrorDetails(val timestamp: Long, val errorCode: String, val message: String)