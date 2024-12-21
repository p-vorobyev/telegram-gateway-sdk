package dev.voroby.telegram.gateway.common.infrastructure

import java.net.http.HttpClient
import java.net.http.HttpRequest

sealed interface Protocol<Request> : AutoCloseable {

    suspend operator fun invoke(request: Request): ProtocolResponse

    companion object {

        fun createHttpProtocol(
            httpClientBuilder: HttpClient.Builder? = null
        ): Protocol<HttpRequest> = HttpProtocol(httpClientBuilder)
    }
}
