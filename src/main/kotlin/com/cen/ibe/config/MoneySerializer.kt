package com.cen.ibe.config

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import java.math.BigDecimal

class MoneySerializer : JsonSerializer<BigDecimal>() {
    override fun serialize(value: BigDecimal?, gen: JsonGenerator?, serializers: SerializerProvider?) {
        gen?.writeString(value?.setScale(2, BigDecimal.ROUND_HALF_UP).toString())
    }
}