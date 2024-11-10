package dev.voroby.telegram.gateway

import arrow.core.Either
import dev.voroby.telegram.gateway.common.domain.Response
import dev.voroby.telegram.gateway.common.infrastructure.HttpProtocol
import dev.voroby.telegram.gateway.common.infrastructure.Protocol
import dev.voroby.telegram.gateway.service.checkSendAbility.CheckSendAbility
import dev.voroby.telegram.gateway.service.checkVerificationStatus.CheckVerificationStatus
import dev.voroby.telegram.gateway.service.sendVerificationMessage.SendVerificationMessage

/* https://core.telegram.org/gateway/api */
interface TelegramGateway : AutoCloseable {

    suspend fun checkSendAbility(request: CheckSendAbility.Request): Either<Throwable, Response>

    suspend fun checkVerificationStatus(request: CheckVerificationStatus.Request): Either<Throwable, Response>

    suspend fun sendVerificationMessage(request: SendVerificationMessage.Request): Either<Throwable, Response>

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