package dev.voroby.telegram.gateway.service.checkVerificationStatus

import dev.voroby.telegram.gateway.common.service.HttpService
import dev.voroby.telegram.gateway.common.service.Service
import dev.voroby.telegram.gateway.service.ServiceName.CHECK_VERIFICATION_STATUS
import dev.voroby.telegram.gateway.service.checkVerificationStatus.CheckVerificationStatus.ExtendedRequest

class CheckVerificationStatusService(private val httpService: HttpService) : Service<ExtendedRequest> {

    override suspend fun invoke(request: ExtendedRequest) = with(request) {
        httpService.execute(
            body = payload,
            serviceName = CHECK_VERIFICATION_STATUS,
            accessToken = accessToken,
            host = gatewayHost
        )
    }
}