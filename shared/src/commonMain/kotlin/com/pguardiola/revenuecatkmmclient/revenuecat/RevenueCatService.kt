package com.pguardiola.revenuecatkmmclient.revenuecat

import com.pguardiola.revenuecatkmmclient.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.path
import kotlin.jvm.JvmSynthetic

/**
 * Allows clients to access RevenueCat's REST API.
 */
class RevenueCatService internal constructor(
    private val ktorClient: HttpClient,
    staging: Boolean,
    private val apiKey: String,
) {

    private val apiHost = if (staging) API_HOST_STAGING else API_HOST_PROD

    /**
     * Get a list of projects.
     */
    @JvmSynthetic
    suspend fun getProjects(): Result<String> {
        return Result.fromCatching {
            ktorClient.get {
                url {
                    protocol = URLProtocol.HTTPS
                    host = apiHost
                    path("v2", API_PATH_PROJECTS)
                }
                headers {
                    append(HttpHeaders.Accept, "application/json")
                }
            }.bodyAsText()
        }
    }

    private companion object {

        private const val API_HOST_STAGING = ""
        private const val API_HOST_PROD = "api.revenuecat.com"
        private const val API_PATH_PROJECTS = "projects"
    }
}
