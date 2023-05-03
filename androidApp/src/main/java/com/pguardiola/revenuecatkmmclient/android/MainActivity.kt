package com.pguardiola.revenuecatkmmclient.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pguardiola.revenuecatkmmclient.RevenueCatHttpClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainActivity : ComponentActivity() {

    private val httpClient by lazy {
        RevenueCatHttpClient(
            getString(R.string.revenuecat_api_key),
            staging = false,
        )
    }
    private val revenueCatProjectsService by lazy {
        httpClient.createProjectsService()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    GreetingView(dataFlow())
                }
            }
        }
    }

    private fun dataFlow(): Flow<String> = flow {
        emit("Loading...")
        val result = revenueCatProjectsService.getProjects()
        emit(result.getOrNull() ?: "null")
    }
}

@Composable
fun GreetingView(flow: Flow<String>) {
    val data = flow.collectAsState(initial = "Loading...")
    Text(text = data.value)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        val flow = flow {
            emit("Hello Android!")
        }
        GreetingView(flow)
    }
}
