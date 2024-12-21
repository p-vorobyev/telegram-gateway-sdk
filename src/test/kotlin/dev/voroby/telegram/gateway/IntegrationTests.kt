package dev.voroby.telegram.gateway

import arrow.core.Either
import dev.voroby.telegram.gateway.common.domain.StatusResponse
import dev.voroby.telegram.gateway.common.infrastructure.Protocol
import dev.voroby.telegram.gateway.checkSendAbility.CheckSendAbility
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.booleans.shouldBeTrue

class IntegrationTests : FunSpec({

    val protocol = Protocol.createHttpProtocol()

    val telegramGateway = TelegramGateway.create(
        accessToken = "your_token",
        protocol = protocol
    )

    fun onProtocolSuccess(statusResponse: StatusResponse) = println(statusResponse.toString())

    afterSpec {
        telegramGateway.close()
    }

    test("Invoke check ability to send the code to the user") {
        val request = CheckSendAbility.Request("phone_number_in_international_format")
        val statusResponse: Either<Throwable, StatusResponse> = telegramGateway.checkSendAbility(request)
        statusResponse.onLeft { println(it.stackTraceToString()) }
        statusResponse.isRight().shouldBeTrue()
        statusResponse.onRight { onProtocolSuccess(it) }
    }
})
