package dev.voroby.telegram.gateway.checkVerificationStatus

import dev.voroby.telegram.gateway.ServiceName.CheckVerificationStatus
import dev.voroby.telegram.gateway.checkVerificationStatus.CheckVerificationStatus.ExtendedRequest
import dev.voroby.telegram.gateway.common.domain.StatusResponse
import dev.voroby.telegram.gateway.common.service.Http
import dev.voroby.telegram.gateway.common.service.Service

class CheckVerificationStatusService(
    private val httpService: Service<Http.Request<*>,StatusResponse>
) : Service<ExtendedRequest, StatusResponse> {

    override suspend fun invoke(request: ExtendedRequest) = with(request) {
        val httpRequest = Http.Request(
            body = payload,
            serviceName = CheckVerificationStatus.serviceName,
            accessToken = accessToken,
            host = gatewayHost
        )
        httpService(httpRequest)
    }
}
