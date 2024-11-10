package dev.voroby.telegram.gateway.common.domain

sealed interface Response {

    data class Success(val result: RequestStatus) : Response

    data class Error(val error: String) : Response
}