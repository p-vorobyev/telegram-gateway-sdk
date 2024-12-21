package dev.voroby.telegram.gateway.common.service

object Http {

    const val NO_RESULT_MESSAGE = "There is no result from the server"

    data class Request<Payload>(
        val body: Payload,
        val serviceName: String,
        val accessToken: String,
        val host: String?
    )
}
