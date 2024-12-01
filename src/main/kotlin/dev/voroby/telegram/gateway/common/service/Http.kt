package dev.voroby.telegram.gateway.common.service

object Http {

    data class Request<Payload>(
        val body: Payload,
        val serviceName: String,
        val accessToken: String,
        val host: String?
    )
}
