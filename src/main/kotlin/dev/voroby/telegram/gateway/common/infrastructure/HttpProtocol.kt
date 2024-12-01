package dev.voroby.telegram.gateway.common.infrastructure

import dev.voroby.telegram.gateway.util.ObjectMapper.fromJson
import kotlinx.coroutines.future.await
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers.ofString
import java.security.SecureRandom
import java.util.concurrent.Executors
import javax.net.ssl.SSLContext

class HttpProtocol(
    private val httpClientBuilder: HttpClient.Builder? = null
) : Protocol<HttpRequest> {

    private val httpClient: HttpClient by lazy { httpClientBuilder?.build() ?: initDefaultHttpClient() }

    override suspend operator fun invoke(request: HttpRequest): ProtocolResponse =
        httpClient.sendAsync(request, ofString())
            .await()
            .let { fromJson(it.body()) }

    override fun close() = httpClient.close()

    private fun initDefaultHttpClient(): HttpClient {
        val virtualThreadExecutor = Executors.newVirtualThreadPerTaskExecutor()
        val defaultSslContext = SSLContext.getInstance(TLS_PROTOCOL)
        defaultSslContext.init(null, Array(1) { TrustAllManager() }, SecureRandom())
        return HttpClient.newBuilder()
            .sslContext(defaultSslContext)
            .executor(virtualThreadExecutor)
            .build()
    }

    private companion object {

        const val TLS_PROTOCOL = "TLSv1.2"
    }
}
