package dev.voroby.telegram.gateway.revokeVerificationMessage

import dev.voroby.telegram.gateway.ServiceName.RevokeVerificationMessage
import dev.voroby.telegram.gateway.common.domain.BooleanResponse
import dev.voroby.telegram.gateway.common.service.Http
import dev.voroby.telegram.gateway.common.service.Service
import dev.voroby.telegram.gateway.revokeVerificationMessage.RevokeVerificationMessage.ExtendedRequest

class RevokeVerificationMessageService(
    private val httpService: Service<Http.Request<*>, BooleanResponse>
) : Service<ExtendedRequest, BooleanResponse> {

    override suspend fun invoke(request: ExtendedRequest) = with(request) {
        val httpRequest = Http.Request(
            body = payload,
            serviceName = RevokeVerificationMessage.serviceName,
            accessToken = accessToken,
            host = gatewayHost
        )
        httpService(httpRequest)
    }
}
