package com.pguardiola.revenuecatkmmclient

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.android.Android
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit

@Suppress("FunctionName")
internal actual fun KtorClient(
    block: HttpClientConfig<out HttpClientEngineConfig>.() -> Unit,
): HttpClient {
    return HttpClient(Android) {
        engine {
            connectTimeout = 30.seconds.toInt(DurationUnit.MILLISECONDS)
            socketTimeout = 30.seconds.toInt(DurationUnit.MILLISECONDS)
        }
        block()
    }
}
