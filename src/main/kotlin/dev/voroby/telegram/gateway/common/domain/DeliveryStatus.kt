package dev.voroby.telegram.gateway.common.domain

import com.fasterxml.jackson.annotation.JsonProperty

data class DeliveryStatus(
    val status: Status,
    @JsonProperty("updated_at") val updatedAt: Int
) {

    enum class Status { sent, read, revoked }
}