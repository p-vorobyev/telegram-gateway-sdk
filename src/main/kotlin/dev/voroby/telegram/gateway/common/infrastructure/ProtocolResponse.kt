package dev.voroby.telegram.gateway.common.infrastructure

import dev.voroby.telegram.gateway.common.domain.RequestStatus

data class ProtocolResponse(
    val ok: Boolean,
    val result: RequestStatus? = null,
    val error: String = ""
)