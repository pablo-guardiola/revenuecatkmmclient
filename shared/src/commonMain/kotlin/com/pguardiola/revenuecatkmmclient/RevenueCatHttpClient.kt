package com.pguardiola.revenuecatkmmclient

import com.pguardiola.revenuecatkmmclient.revenuecat.RevenueCatService
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

@Suppress("FunctionName")
internal expect fun KtorClient(
    block: HttpClientConfig<out HttpClientEngineConfig>.() -> Unit,
): HttpClient

/**
 * Asynchronous client to perform HTTP requests.
 */
class RevenueCatHttpClient(
    private val apiKey: String,
    private val staging: Boolean,
) {

    @OptIn(ExperimentalSerializationApi::class)
    private val ktorClient = KtorClient {
        expectSuccess = true
        install(ContentNegotiation) {
            val jsonConfig = Json {
                explicitNulls = false
                ignoreUnknownKeys = true
            }
            json(jsonConfig)
        }
        install(Auth) {
            bearer {
                loadTokens {
                    BearerTokens(apiKey, apiKey)
                }
            }
        }
    }

    /**
     * Creates a [RevenueCatService] instance.
     */
    fun createProjectsService(): RevenueCatService {
        return RevenueCatService(ktorClient, staging, apiKey)
    }

    /**
     * Closes the underlying [ktorClient].
     */
    fun close() {
        ktorClient.close()
    }
}
