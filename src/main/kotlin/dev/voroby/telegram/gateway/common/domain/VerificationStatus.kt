package dev.voroby.telegram.gateway.common.domain

import com.fasterxml.jackson.annotation.JsonProperty

data class VerificationStatus(
    val status: Status,
    @JsonProperty("updated_at") val updatedAt: Int,
    @JsonProperty("code_entered") val codeEntered: String? = null,
) {

    enum class Status { code_valid, code_invalid, code_max_attempts_exceeded, expired }
}
