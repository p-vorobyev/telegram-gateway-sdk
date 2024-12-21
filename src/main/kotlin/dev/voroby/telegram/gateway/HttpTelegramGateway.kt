package dev.voroby.telegram.gateway

import dev.voroby.telegram.gateway.common.domain.StatusResponse
import dev.voroby.telegram.gateway.common.infrastructure.HttpProtocol
import dev.voroby.telegram.gateway.common.service.Http
import dev.voroby.telegram.gateway.common.service.HttpService
import dev.voroby.telegram.gateway.common.service.Service
import dev.voroby.telegram.gateway.checkSendAbility.CheckSendAbility
import dev.voroby.telegram.gateway.checkSendAbility.CheckSendAbilityService
import dev.voroby.telegram.gateway.checkVerificationStatus.CheckVerificationStatus
import dev.voroby.telegram.gateway.checkVerificationStatus.CheckVerificationStatusService
import dev.voroby.telegram.gateway.common.domain.BooleanResponse
import dev.voroby.telegram.gateway.revokeVerificationMessage.RevokeVerificationMessage
import dev.voroby.telegram.gateway.revokeVerificationMessage.RevokeVerificationMessageService
import dev.voroby.telegram.gateway.sendVerificationMessage.SendVerificationMessage
import dev.voroby.telegram.gateway.sendVerificationMessage.SendVerificationMessageService
import dev.voroby.telegram.gateway.revokeVerificationMessage.HttpService as RevokeVerificationHttpService

class HttpTelegramGateway(
    private val protocol: HttpProtocol,
    private val accessToken: String,
    private val gatewayHost: String?
): TelegramGateway {

    private val commonHttpService: Service<Http.Request<*>, StatusResponse> by lazy { HttpService(protocol) }

    private val revokeVerificationHttpService: Service<Http.Request<*>, BooleanResponse> by lazy {
        RevokeVerificationHttpService(protocol)
    }

    private val checkSendAbilityService by lazy { CheckSendAbilityService(commonHttpService) }

    private val checkVerificationStatusService by lazy { CheckVerificationStatusService(commonHttpService) }

    private val sendVerificationMessageService by lazy { SendVerificationMessageService(commonHttpService) }

    private val revokeVerificationMessageService by lazy {
        RevokeVerificationMessageService(revokeVerificationHttpService)
    }

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

    override suspend fun revokeVerificationMessage(request: RevokeVerificationMessage.Request) =
        revokeVerificationMessageService.invoke(
            RevokeVerificationMessage.ExtendedRequest(
                accessToken = accessToken,
                gatewayHost = gatewayHost,
                payload = request
            )
        )

    override fun close() {
        protocol.close()
    }
}
