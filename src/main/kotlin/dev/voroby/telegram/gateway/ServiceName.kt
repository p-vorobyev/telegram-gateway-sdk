package dev.voroby.telegram.gateway

enum class ServiceName(val serviceName: String) {

    SendVerificationMessage("sendVerificationMessage"),
    CheckSendAbility("checkSendAbility"),
    CheckVerificationStatus("checkVerificationStatus"),
    RevokeVerificationMessage("revokeVerificationMessage")
}
