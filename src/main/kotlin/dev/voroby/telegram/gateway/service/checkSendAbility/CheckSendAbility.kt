package dev.voroby.telegram.gateway.service.checkSendAbility

import com.fasterxml.jackson.annotation.JsonProperty

object CheckSendAbility {

    data class Request(@JsonProperty("phone_number") val phoneNumber: String)

    data class ExtendedRequest(
        val accessToken: String,
        val gatewayHost: String? = null,
        val payload: Request
    )
}
