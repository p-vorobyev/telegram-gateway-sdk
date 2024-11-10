package dev.voroby.telegram.gateway.common.service

import arrow.core.Either
import dev.voroby.telegram.gateway.common.domain.Response

interface Service<T> {

    suspend operator fun invoke(request: T): Either<Throwable, Response>
}