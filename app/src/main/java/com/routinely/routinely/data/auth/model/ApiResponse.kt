package com.routinely.routinely.data.auth.model

import io.ktor.http.HttpStatusCode
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val message: List<String>,
    @Contextual var serverStatusCode: HttpStatusCode,
)

@Serializable
internal data class ResponseStringTemp(
    val message: String,
)