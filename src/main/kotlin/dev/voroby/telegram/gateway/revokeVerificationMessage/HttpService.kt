package dev.voroby.telegram.gateway.revokeVerificationMessage

import arrow.core.Either
import arrow.core.Either.Companion.catch
import dev.voroby.telegram.gateway.common.domain.BooleanResponse
import dev.voroby.telegram.gateway.common.domain.BooleanResponse.Error
import dev.voroby.telegram.gateway.common.domain.BooleanResponse.Success
import dev.voroby.telegram.gateway.common.infrastructure.Protocol
import dev.voroby.telegram.gateway.common.service.Http
import dev.voroby.telegram.gateway.common.service.Http.NO_RESULT_MESSAGE
import dev.voroby.telegram.gateway.common.service.Service
import dev.voroby.telegram.gateway.util.Http.buildPostRequest
import java.net.http.HttpRequest

class HttpService(private val protocol: Protocol<HttpRequest>) : Service<Http.Request<*>, BooleanResponse> {

    override suspend fun invoke(request: Http.Request<*>): Either<Throwable, BooleanResponse> = with(request) {
        catch {
            val protocolRequest = buildPostRequest(body, serviceName, accessToken, host)
            val baseResponse = protocol(protocolRequest)
            if (baseResponse.ok)
                baseResponse.result?.let { Success(it.toBoolean()) } ?: Error(NO_RESULT_MESSAGE)
            else
                Error(baseResponse.error)
        }
    }
}
