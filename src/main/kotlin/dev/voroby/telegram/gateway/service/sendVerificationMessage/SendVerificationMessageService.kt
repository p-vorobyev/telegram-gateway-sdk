package dev.voroby.telegram.gateway.service.sendVerificationMessage

import dev.voroby.telegram.gateway.common.service.Http
import dev.voroby.telegram.gateway.common.service.Service
import dev.voroby.telegram.gateway.service.ServiceName.SEND_VERIFICATION_MESSAGE
import dev.voroby.telegram.gateway.service.sendVerificationMessage.SendVerificationMessage.ExtendedRequest

class SendVerificationMessageService(
    private val httpService: Service<Http.Request<*>>
) : Service<ExtendedRequest> {

    override suspend fun invoke(request: ExtendedRequest) = with(request) {
        val httpRequest = Http.Request(
            body = payload,
            serviceName = SEND_VERIFICATION_MESSAGE,
            accessToken = accessToken,
            host = gatewayHost
        )
        httpService.invoke(httpRequest)
    }
}
