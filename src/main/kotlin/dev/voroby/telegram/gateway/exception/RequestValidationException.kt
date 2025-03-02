package dev.voroby.telegram.gateway.exception

import dev.voroby.telegram.gateway.ServiceName

class RequestValidationException(
    serviceName: ServiceName,
    field: String
) : RuntimeException("Request validation failed: [service: ${serviceName.serviceName}, field: $field]")
