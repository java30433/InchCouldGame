package inch.cg.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import bakuen.lib.navigator.LocalNavigator
import bakuen.lib.navigator.currentOrThrow
import bakuen.wear.components.Text
import bakuen.wear.components.material3.CircularProgressIndicator
import inch.cg.screens.login.LoginScreen
import inch.cg.utils.MyPreview
import inch.cg.webview.API

private data class State(
    val webLoading: Boolean = true,
    val userName: String? = null
)

@Composable
fun MainScreen() {
    var state by remember { mutableStateOf(State()) }
    LaunchedEffect(Unit) {
        API.HomePage.waitLoadDone()
        state = state.copy(webLoading = false)
        state = state.copy(userName = API.HomePage.getUserName())
    }
    MainScreenUI(state = state)
}

@Composable
private fun MainScreenUI(state: State) {
    if (state.webLoading) {
        Loading()
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            state.userName?.let { 
                Text(text = "当前登录为：$it")
            } ?: LocalNavigator.currentOrThrow.push { LoginScreen() }
        }
    }
}

@Composable
private fun Loading() {
    Column {
        CircularProgressIndicator()
        Text(text = "正在加载中...")
    }
}

@MyPreview
@Composable
private fun PreviewLoading() {
    MainScreenUI(state = State())
}

@MyPreview
@Composable
private fun PreviewMain() {
    MainScreenUI(state = State(
        webLoading = false,
        userName = "java30433"
    ))
}