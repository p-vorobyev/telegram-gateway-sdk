package dev.voroby.telegram.gateway.revokeVerificationMessage

import com.fasterxml.jackson.annotation.JsonProperty

object RevokeVerificationMessage {

    data class Request(@JsonProperty("request_id") val requestId: String)

    data class ExtendedRequest(
        val accessToken: String,
        val gatewayHost: String? = null,
        val payload: Request
    )
}
