package dev.voroby.telegram.gateway.sendVerificationMessage

import arrow.core.Either
import arrow.core.Either.Companion.catch
import arrow.core.flatMap
import dev.voroby.telegram.gateway.ServiceName
import dev.voroby.telegram.gateway.common.domain.StatusResponse
import dev.voroby.telegram.gateway.common.service.Service
import dev.voroby.telegram.gateway.exception.RequestValidationException
import dev.voroby.telegram.gateway.sendVerificationMessage.SendVerificationMessage.ExtendedRequest

class ValidationServiceDecorator(
    private val delegate: Service<ExtendedRequest, StatusResponse>
) : Service<ExtendedRequest, StatusResponse> {

    override suspend fun invoke(request: ExtendedRequest): Either<Throwable, StatusResponse> =
        catch { validate(request) }.flatMap { delegate.invoke(request) }

    private fun validate(request: ExtendedRequest) = with(request.payload) {
        codeLength?.let(::checkCodeLength)
        callbackUrl?.let(::checkCallbackUrl)
        payload?.let(::checkPayload)
        ttl?.let(::checkTtl)
    }

    private fun checkCodeLength(codeLength: Int) {
        if (codeLength < CODE_LENGTH_MIN_VALUE || codeLength > CODE_LENGTH_MAX_VALUE) {
            throw validationException(field = "codeLength")
        }
    }

    private fun checkCallbackUrl(callbackUrl: String) {
        if (callbackUrl.encodeToByteArray().size > CALLBACK_URL_MAX_SIZE_IN_BYTES) {
            throw validationException(field = "callbackUrl")
        }
    }

    private fun checkPayload(payload: String) {
        if (payload.encodeToByteArray().size > PAYLOAD_MAX_SIZE_IN_BYTES) {
            throw validationException(field = "payload")
        }
    }

    private fun checkTtl(ttl: Int) {
        if (ttl < TTL_SECONDS_MIN_VALUE || ttl > TTL_SECONDS_MAX_VALUE) {
            throw validationException(field = "ttl")
        }
    }

    private fun validationException(field: String) =
        RequestValidationException(ServiceName.SendVerificationMessage, field)

    private companion object {

        const val CODE_LENGTH_MIN_VALUE = 4

        const val CODE_LENGTH_MAX_VALUE = 8

        const val CALLBACK_URL_MAX_SIZE_IN_BYTES = 256

        const val PAYLOAD_MAX_SIZE_IN_BYTES = 128

        const val TTL_SECONDS_MIN_VALUE = 30

        const val TTL_SECONDS_MAX_VALUE = 3600
    }
}

fun Service<ExtendedRequest, StatusResponse>.validateRequest() = ValidationServiceDecorator(this)
