package dev.voroby.telegram.gateway.service.sendVerificationMessage

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL
import com.fasterxml.jackson.annotation.JsonProperty

object SendVerificationMessage {

    @JsonInclude(NON_NULL)
    data class Request(
        @JsonProperty("phone_number")
        val phoneNumber: String,
        @JsonProperty("request_id")
        val requestId: String? = null,
        @JsonProperty("sender_username")
        val senderUsername: String? = null,
        val code: String? = null,
        @JsonProperty("code_length")
        val codeLength: Int? = null,
        @JsonProperty("callback_url")
        val callbackUrl: String? = null,
        val payload: String? = null,
        val ttl: Int? = null
    )

    data class ExtendedRequest(
        val accessToken: String,
        val gatewayHost: String? = null,
        val payload: Request
    )
}
