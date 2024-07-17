package bakuen.wear.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.TextFieldDecorator
import androidx.compose.foundation.text2.input.TextFieldLineLimits
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TextField(
    modifier: Modifier = Modifier,
    state: TextFieldState,
    textStyle: TextStyle = Theme.typo.body,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    lineLimits: TextFieldLineLimits = TextFieldLineLimits.Default,
    decorator: TextFieldDecorator? = null,
) {
    Box(modifier = modifier) {
        BasicTextField2(
            modifier = Modifier.background(color = Theme.color.surfaceVariant),
            state = state,
            textStyle = textStyle,
            keyboardActions = keyboardActions,
            keyboardOptions = keyboardOptions,
            lineLimits = lineLimits,
            decorator = decorator
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TextField(
    modifier: Modifier = Modifier,
    state: TextFieldState,
    textStyle: TextStyle = Theme.typo.body,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    lineLimits: TextFieldLineLimits = TextFieldLineLimits.Default,
    hint: String
) {
    TextField(
        modifier = Modifier,
        state = state,
        textStyle = textStyle,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        lineLimits = lineLimits,
        decorator = { innerInputField ->
            if (state.text.isEmpty()) Text(text = hint, color = Theme.color.onSurfaceVariant)
            else innerInputField()
        }
    )
}