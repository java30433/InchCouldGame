package inch.cg.screens.login

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import bakuen.wear.components.Text
import bakuen.wear.components.TextField
import bakuen.wear.components.Theme

private class State()
private sealed class Event {}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoginScreen() {
    val accountFieldState = rememberTextFieldState()
    val pwdFieldState = rememberTextFieldState()
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(text = "登录网易云游戏", style = Theme.typo.smallTitle)
        TextField(state = accountFieldState, hint = "请输入手机号码")
        TextField(state = pwdFieldState, hint = "请输入验证码")
    }
}