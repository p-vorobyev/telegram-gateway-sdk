package dev.voroby.telegram.gateway.service.checkSendAbility

import dev.voroby.telegram.gateway.common.service.HttpService
import dev.voroby.telegram.gateway.common.service.Service
import dev.voroby.telegram.gateway.service.ServiceName.CHECK_SEND_ABILITY
import dev.voroby.telegram.gateway.service.checkSendAbility.CheckSendAbility.ExtendedRequest

class CheckSendAbilityService(private val httpService: HttpService) : Service<ExtendedRequest> {

    override suspend fun invoke(request: ExtendedRequest) = with(request) {
        httpService.execute(
            body = payload,
            serviceName = CHECK_SEND_ABILITY,
            accessToken = accessToken,
            host = gatewayHost
        )
    }
}