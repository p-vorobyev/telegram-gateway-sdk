package dev.voroby.telegram.gateway.common.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class RequestStatus(
    @param:JsonProperty("request_id") val requestId: String,
    @param:JsonProperty("phone_number") val phoneNumber: String,
    @param:JsonProperty("request_cost") val requestCost: Double,
    @param:JsonProperty("is_refunded") val isRefunded: Boolean? = null,
    @param:JsonProperty("remaining_balance") val remainingBalance: Double? = null,
    @param:JsonProperty("delivery_status") val deliveryStatus: DeliveryStatus? = null,
    @param:JsonProperty("verification_status") val verificationStatus: VerificationStatus? = null,
    val payload: String? = null
)
