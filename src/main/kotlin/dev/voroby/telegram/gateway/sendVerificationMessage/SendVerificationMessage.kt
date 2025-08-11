package dev.voroby.telegram.gateway.sendVerificationMessage

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL
import com.fasterxml.jackson.annotation.JsonProperty

object SendVerificationMessage {

    @JsonInclude(NON_NULL)
    data class Request(
        @param:JsonProperty("phone_number")
        val phoneNumber: String,
        @param:JsonProperty("request_id")
        val requestId: String? = null,
        @param:JsonProperty("sender_username")
        val senderUsername: String? = null,
        val code: String? = null,
        @param:JsonProperty("code_length")
        val codeLength: Int? = null,
        @param:JsonProperty("callback_url")
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
