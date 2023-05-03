package com.pguardiola.revenuecatkmmclient

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.darwin.Darwin

@Suppress("FunctionName")
internal actual fun KtorClient(
    block: HttpClientConfig<out HttpClientEngineConfig>.() -> Unit,
): HttpClient {
    return HttpClient(Darwin) {
        engine {
            configureSession {
                timeoutIntervalForRequest = 30.0
                timeoutIntervalForResource = 30.0
            }
        }
        block()
    }
}
