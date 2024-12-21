package dev.voroby.telegram.gateway.common.infrastructure

import com.fasterxml.jackson.databind.JsonNode
import dev.voroby.telegram.gateway.util.ObjectMapper.mapper

data class ProtocolResponse(
    val ok: Boolean,
    val result: String? = null,
    val error: String = ""
) {

    companion object {

        fun fromJson(json: String): ProtocolResponse {
            val jsonNode = mapper.readTree(json)
            val errorFieldNode: JsonNode? = jsonNode.get("error")
            return ProtocolResponse(
                ok = jsonNode.get("ok").asBoolean(),
                result = jsonNode.get("result")?.toString(),
                error = if (errorFieldNode != null) errorFieldNode.asText() else ""
            )
        }
    }
}
