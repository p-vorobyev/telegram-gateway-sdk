package dev.voroby.telegram.gateway.checkSendAbility

import dev.voroby.telegram.gateway.ServiceName.CheckSendAbility
import dev.voroby.telegram.gateway.checkSendAbility.CheckSendAbility.ExtendedRequest
import dev.voroby.telegram.gateway.common.domain.StatusResponse
import dev.voroby.telegram.gateway.common.service.Http
import dev.voroby.telegram.gateway.common.service.Service

class CheckSendAbilityService(
    private val httpService: Service<Http.Request<*>, StatusResponse>
) : Service<ExtendedRequest, StatusResponse> {

    override suspend fun invoke(request: ExtendedRequest) = with(request) {
        val httpRequest = Http.Request(
            body = payload,
            serviceName = CheckSendAbility.serviceName,
            accessToken = accessToken,
            host = gatewayHost
        )
        httpService(httpRequest)
    }
}
