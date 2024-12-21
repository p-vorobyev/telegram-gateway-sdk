package dev.voroby.telegram.gateway.common.domain

sealed interface StatusResponse {

    data class Success(val result: RequestStatus) : StatusResponse

    data class Error(val error: String) : StatusResponse
}
