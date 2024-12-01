package dev.voroby.telegram.gateway.util

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

object ObjectMapper {

    val mapper by lazy { jacksonObjectMapper() }

    fun <T> toJson(obj: T): String = mapper.writeValueAsString(obj)

    inline fun <reified T> fromJson(json: String): T = mapper.readValue(json, T::class.java)
}
