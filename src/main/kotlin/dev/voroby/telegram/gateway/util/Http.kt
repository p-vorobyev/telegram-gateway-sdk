package dev.voroby.telegram.gateway.util

import dev.voroby.telegram.gateway.util.ObjectMapper.toJson
import java.net.URI
import java.net.http.HttpRequest
import java.net.http.HttpRequest.BodyPublishers.ofString

object Http {

    private const val DEFAULT_GATEWAY_HOST = "gatewayapi.telegram.org"

    private const val HEADER_AUTHORIZATION = "Authorization"

    private const val HEADER_CONTENT_TYPE = "Content-Type"

    private const val CONTENT_TYPE_JSON_VALUE = "application/json"

    fun <Payload> buildPostRequest(
        body: Payload,
        serviceName: String,
        accessToken: String,
        host: String?
    ): HttpRequest = HttpRequest.newBuilder(URI("https://${host ?: DEFAULT_GATEWAY_HOST}/$serviceName"))
        .POST(ofString(toJson(body)))
        .header(HEADER_CONTENT_TYPE, CONTENT_TYPE_JSON_VALUE)
        .header(HEADER_AUTHORIZATION, "Bearer $accessToken")
        .build()
}
