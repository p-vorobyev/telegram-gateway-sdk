package dev.voroby.telegram.gateway.common.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class RequestStatus(
    @JsonProperty("request_id") val requestId: String,
    @JsonProperty("phone_number") val phoneNumber: String,
    @JsonProperty("request_cost") val requestCost: Double,
    @JsonProperty("is_refunded") val isRefunded: Boolean? = null,
    @JsonProperty("remaining_balance") val remainingBalance: Double? = null,
    @JsonProperty("delivery_status") val deliveryStatus: DeliveryStatus? = null,
    @JsonProperty("verification_status") val verificationStatus: VerificationStatus? = null,
    val payload: String? = null
)
