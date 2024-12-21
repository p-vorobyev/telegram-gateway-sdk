package dev.voroby.telegram.gateway.common.service

import arrow.core.Either
import arrow.core.Either.Companion.catch
import dev.voroby.telegram.gateway.common.domain.RequestStatus
import dev.voroby.telegram.gateway.common.domain.StatusResponse
import dev.voroby.telegram.gateway.common.domain.StatusResponse.Error
import dev.voroby.telegram.gateway.common.domain.StatusResponse.Success
import dev.voroby.telegram.gateway.common.infrastructure.Protocol
import dev.voroby.telegram.gateway.common.service.Http.NO_RESULT_MESSAGE
import dev.voroby.telegram.gateway.util.Http.buildPostRequest
import dev.voroby.telegram.gateway.util.ObjectMapper.fromJson
import java.net.http.HttpRequest

class HttpService(private val protocol: Protocol<HttpRequest>) : Service<Http.Request<*>, StatusResponse> {

    override suspend fun invoke(request: Http.Request<*>): Either<Throwable, StatusResponse> = with(request) {
        catch {
            val protocolRequest = buildPostRequest(body, serviceName, accessToken, host)
            val baseResponse = protocol(protocolRequest)
            if (baseResponse.ok)
                baseResponse.result?.let { Success(fromJson<RequestStatus>(it)) } ?: Error(NO_RESULT_MESSAGE)
            else
                Error(baseResponse.error)
        }
    }
}
