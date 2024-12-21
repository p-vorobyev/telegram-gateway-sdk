package dev.voroby.telegram.gateway

import arrow.core.Either
import dev.voroby.telegram.gateway.common.domain.StatusResponse
import dev.voroby.telegram.gateway.common.infrastructure.HttpProtocol
import dev.voroby.telegram.gateway.common.infrastructure.Protocol
import dev.voroby.telegram.gateway.checkSendAbility.CheckSendAbility
import dev.voroby.telegram.gateway.checkVerificationStatus.CheckVerificationStatus
import dev.voroby.telegram.gateway.common.domain.BooleanResponse
import dev.voroby.telegram.gateway.revokeVerificationMessage.RevokeVerificationMessage
import dev.voroby.telegram.gateway.sendVerificationMessage.SendVerificationMessage

/* https://core.telegram.org/gateway/api */
interface TelegramGateway : AutoCloseable {

    suspend fun checkSendAbility(
        request: CheckSendAbility.Request
    ): Either<Throwable, StatusResponse>

    suspend fun checkVerificationStatus(
        request: CheckVerificationStatus.Request
    ): Either<Throwable, StatusResponse>

    suspend fun sendVerificationMessage(
        request: SendVerificationMessage.Request
    ): Either<Throwable, StatusResponse>

    suspend fun revokeVerificationMessage(
        request: RevokeVerificationMessage.Request
    ): Either<Throwable, BooleanResponse>

    companion object {

        fun create(
            protocol: Protocol<*>,
            accessToken: String,
            gatewayHost: String? = null
        ): TelegramGateway = when (protocol) {
            is HttpProtocol -> HttpTelegramGateway(protocol, accessToken, gatewayHost)
        }
    }
}
