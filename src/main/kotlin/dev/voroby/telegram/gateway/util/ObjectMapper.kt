package dev.voroby.telegram.gateway.util

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

object ObjectMapper {

    val objectMapper by lazy { jacksonObjectMapper() }

    fun <T> toJson(obj: T): String = objectMapper.writeValueAsString(obj)

    inline fun <reified T> fromJson(json: String): T = objectMapper.readValue(json, T::class.java)
}