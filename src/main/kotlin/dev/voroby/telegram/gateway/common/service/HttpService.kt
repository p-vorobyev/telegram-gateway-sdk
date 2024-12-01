package dev.voroby.telegram.gateway.common.service

import arrow.core.Either
import arrow.core.Either.Companion.catch
import dev.voroby.telegram.gateway.common.domain.Response
import dev.voroby.telegram.gateway.common.domain.Response.Error
import dev.voroby.telegram.gateway.common.domain.Response.Success
import dev.voroby.telegram.gateway.common.infrastructure.Protocol
import dev.voroby.telegram.gateway.util.Http.DEFAULT_GATEWAY_HOST
import dev.voroby.telegram.gateway.util.ObjectMapper.toJson
import java.net.URI
import java.net.http.HttpRequest
import java.net.http.HttpRequest.BodyPublishers.ofString

class HttpService(private val protocol: Protocol<HttpRequest>) : Service<Http.Request<*>> {

    override suspend fun invoke(request: Http.Request<*>): Either<Throwable, Response> = with(request) {
        catch {
            val protocolRequest = httpRequest(body, serviceName, accessToken, host)
            val baseResponse = protocol(protocolRequest)
            if (baseResponse.ok)
                baseResponse.result?.let { Success(it) } ?: Error(NO_RESULT_MESSAGE)
            else
                Error(baseResponse.error)
        }
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
