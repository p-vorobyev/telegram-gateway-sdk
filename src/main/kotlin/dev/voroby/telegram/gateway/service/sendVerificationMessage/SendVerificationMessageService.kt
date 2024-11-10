package dev.voroby.telegram.gateway.service.sendVerificationMessage

import dev.voroby.telegram.gateway.common.service.HttpService
import dev.voroby.telegram.gateway.common.service.Service
import dev.voroby.telegram.gateway.service.ServiceName.SEND_VERIFICATION_MESSAGE
import dev.voroby.telegram.gateway.service.sendVerificationMessage.SendVerificationMessage.ExtendedRequest

class SendVerificationMessageService(private val httpService: HttpService) : Service<ExtendedRequest> {

    override suspend fun invoke(request: ExtendedRequest) = with(request) {
        httpService.execute(
            body = payload,
            serviceName = SEND_VERIFICATION_MESSAGE,
            accessToken = accessToken,
            host = gatewayHost
        )
    }
}