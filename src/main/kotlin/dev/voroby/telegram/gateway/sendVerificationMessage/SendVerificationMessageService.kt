package dev.voroby.telegram.gateway.sendVerificationMessage

import dev.voroby.telegram.gateway.ServiceName.SendVerificationMessage
import dev.voroby.telegram.gateway.common.domain.StatusResponse
import dev.voroby.telegram.gateway.common.service.Http
import dev.voroby.telegram.gateway.common.service.Service
import dev.voroby.telegram.gateway.sendVerificationMessage.SendVerificationMessage.ExtendedRequest

class SendVerificationMessageService(
    private val httpService: Service<Http.Request<*>, StatusResponse>
) : Service<ExtendedRequest, StatusResponse> {

    override suspend fun invoke(request: ExtendedRequest) = with(request) {
        val httpRequest = Http.Request(
            body = payload,
            serviceName = SendVerificationMessage.serviceName,
            accessToken = accessToken,
            host = gatewayHost
        )
        httpService(httpRequest)
    }
}
