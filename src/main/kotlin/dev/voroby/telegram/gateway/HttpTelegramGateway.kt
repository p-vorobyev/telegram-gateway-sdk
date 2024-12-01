package dev.voroby.telegram.gateway

import dev.voroby.telegram.gateway.common.infrastructure.HttpProtocol
import dev.voroby.telegram.gateway.common.service.Http
import dev.voroby.telegram.gateway.common.service.HttpService
import dev.voroby.telegram.gateway.common.service.Service
import dev.voroby.telegram.gateway.service.checkSendAbility.CheckSendAbility
import dev.voroby.telegram.gateway.service.checkSendAbility.CheckSendAbilityService
import dev.voroby.telegram.gateway.service.checkVerificationStatus.CheckVerificationStatus
import dev.voroby.telegram.gateway.service.checkVerificationStatus.CheckVerificationStatusService
import dev.voroby.telegram.gateway.service.sendVerificationMessage.SendVerificationMessage
import dev.voroby.telegram.gateway.service.sendVerificationMessage.SendVerificationMessageService

class HttpTelegramGateway(
    private val protocol: HttpProtocol,
    private val accessToken: String,
    private val gatewayHost: String?
): TelegramGateway {

    private val httpService: Service<Http.Request<*>> by lazy { HttpService(protocol) }

    private val checkSendAbilityService by lazy { CheckSendAbilityService(httpService) }

    private val checkVerificationStatusService by lazy { CheckVerificationStatusService(httpService) }

    private val sendVerificationMessageService by lazy { SendVerificationMessageService(httpService) }

    override suspend fun checkSendAbility(request: CheckSendAbility.Request) =
        checkSendAbilityService.invoke(
            CheckSendAbility.ExtendedRequest(
                accessToken = accessToken,
                gatewayHost = gatewayHost,
                payload = request
            )
        )

    override suspend fun checkVerificationStatus(request: CheckVerificationStatus.Request) =
        checkVerificationStatusService.invoke(
            CheckVerificationStatus.ExtendedRequest(
                accessToken = accessToken,
                gatewayHost = gatewayHost,
                payload = request
            )
        )

    override suspend fun sendVerificationMessage(request: SendVerificationMessage.Request) =
        sendVerificationMessageService.invoke(
            SendVerificationMessage.ExtendedRequest(
                accessToken = accessToken,
                gatewayHost = gatewayHost,
                payload = request
            )
        )

    override fun close() {
        protocol.close()
    }
}
