package dev.voroby.telegram.gateway.common.service

import arrow.core.Either

interface Service<Request, Response> {

    suspend operator fun invoke(request: Request): Either<Throwable, Response>
}
