package dev.voroby.telegram.gateway.common.domain

sealed interface BooleanResponse {

    data class Success(val result: Boolean) : BooleanResponse

    data class Error(val error: String) : BooleanResponse
}
