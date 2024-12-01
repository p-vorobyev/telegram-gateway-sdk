package dev.voroby.telegram.gateway

import arrow.core.Either
import dev.voroby.telegram.gateway.common.domain.Response
import dev.voroby.telegram.gateway.common.infrastructure.Protocol
import dev.voroby.telegram.gateway.service.checkSendAbility.CheckSendAbility
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.booleans.shouldBeTrue

class IntegrationTests : FunSpec({

    val protocol = Protocol.createHttpProtocol()

    val telegramGateway = TelegramGateway.create(
        accessToken = "your_token",
        protocol = protocol
    )

    fun onProtocolSuccess(response: Response) = println(response.toString())

    afterSpec {
        telegramGateway.close()
    }

    test("Invoke check ability to send the code to the user") {
        val request = CheckSendAbility.Request("phone_number_in_international_format")
        val response: Either<Throwable, Response> = telegramGateway.checkSendAbility(request)
        response.isRight().shouldBeTrue()
        response.onRight { onProtocolSuccess(it) }
    }
})
