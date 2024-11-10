package dev.voroby.telegram.gateway.common.service

import arrow.core.Either
import arrow.core.Either.Companion.catch
import dev.voroby.telegram.gateway.common.domain.Response
import dev.voroby.telegram.gateway.common.domain.Response.Error
import dev.voroby.telegram.gateway.common.domain.Response.Success
import dev.voroby.telegram.gateway.common.infrastructure.HttpProtocol
import dev.voroby.telegram.gateway.util.Http.Companion.DEFAULT_GATEWAY_HOST
import dev.voroby.telegram.gateway.util.ObjectMapper.toJson
import java.net.URI
import java.net.http.HttpRequest
import java.net.http.HttpRequest.BodyPublishers.ofString

class HttpService(private val protocol: HttpProtocol) {

    suspend fun <Payload> execute(
        body: Payload,
        serviceName: String,
        accessToken: String,
        host: String?
    ): Either<Throwable, Response> = catch {
        val request = httpRequest(body, serviceName, accessToken, host)
        val baseResponse = protocol(request)
        if (baseResponse.ok)
            baseResponse.result?.let { Success(it) } ?: Error(NO_RESULT_MESSAGE)
        else
            Error(baseResponse.error)
    }

    private fun <Payload> httpRequest(
        body: Payload,
        serviceName: String,
        accessToken: String,
        host: String?
    ) = HttpRequest.newBuilder(URI("https://${host ?: DEFAULT_GATEWAY_HOST}/$serviceName"))
        .POST(ofString(toJson(body)))
        .header("Content-Type", "application/json")
        .header("Authorization", "Bearer $accessToken")
        .build()

    private companion object {

        const val NO_RESULT_MESSAGE = "There is no result from the server"
    }
}