package dev.voroby.telegram.gateway.service.checkVerificationStatus

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL
import com.fasterxml.jackson.annotation.JsonProperty

object CheckVerificationStatus {

    @JsonInclude(NON_NULL)
    data class Request(
        @JsonProperty("request_id") val requestId: String,
        val code: String?
    )

    data class ExtendedRequest(
        val accessToken: String,
        val gatewayHost: String? = null,
        val payload: Request
    )
}