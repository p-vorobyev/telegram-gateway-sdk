package dev.voroby.telegram.gateway.common.infrastructure

import java.security.cert.X509Certificate
import javax.net.ssl.X509TrustManager

class TrustAllManager: X509TrustManager {

    override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {
        /*skip check*/
    }

    override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
        /*skip check*/
    }

    override fun getAcceptedIssuers(): Array<X509Certificate> {
        return emptyArray()
    }
}
