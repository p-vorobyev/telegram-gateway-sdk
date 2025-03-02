package dev.voroby.telegram.gateway.common.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class VerificationStatus(
    val status: Status,
    @JsonProperty("updated_at") val updatedAt: Int,
    @JsonProperty("code_entered") val codeEntered: String? = null,
) {

    enum class Status {
        @JsonProperty("code_valid") CodeValid,
        @JsonProperty("code_invalid") CodeInvalid,
        @JsonProperty("code_max_attempts_exceeded") CodeMaxAttemptsExceeded,
        @JsonProperty("expired") Expired
    }
}
