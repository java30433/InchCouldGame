package bakuen.wear.components

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit

val LocalTextStyle = staticCompositionLocalOf { Theme.typo.body }
@Composable
fun Text(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = LocalTextStyle.current,
    fontSize: TextUnit = style.fontSize,
    color: Color = style.color,
) {
    BasicText(
        modifier = modifier, text = text, style = style.copy(
            color = color,
            fontSize = fontSize,
        )
    )
}