package dev.voroby.telegram.gateway.common.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class DeliveryStatus(
    val status: Status,
    @JsonProperty("updated_at") val updatedAt: Int
) {

    enum class Status {
        @JsonProperty("sent") Sent,
        @JsonProperty("delivered") Delivered,
        @JsonProperty("read") Read,
        @JsonProperty("expired") Expired,
        @JsonProperty("revoked") Revoked
    }
}
