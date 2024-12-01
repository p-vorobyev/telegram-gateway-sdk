package dev.voroby.telegram.gateway.service.checkSendAbility

import dev.voroby.telegram.gateway.common.service.Http
import dev.voroby.telegram.gateway.common.service.Service
import dev.voroby.telegram.gateway.service.ServiceName.CHECK_SEND_ABILITY
import dev.voroby.telegram.gateway.service.checkSendAbility.CheckSendAbility.ExtendedRequest

class CheckSendAbilityService(
    private val httpService: Service<Http.Request<*>>
) : Service<ExtendedRequest> {

    override suspend fun invoke(request: ExtendedRequest) = with(request) {
        val httpRequest = Http.Request(
            body = payload,
            serviceName = CHECK_SEND_ABILITY,
            accessToken = accessToken,
            host = gatewayHost
        )
        httpService.invoke(httpRequest)
    }
}
