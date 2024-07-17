package bakuen.wear.components

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

object Theme {
    val color = ColorScheme(
        primary = Color(0xFF_a8e6cf),
        onPrimary = Color(0xFF_1f4c3c),
        primaryContainer = Color(0xFF_296650),
        onPrimaryContainer = Color(0xFF_bae6d6),
        surface = Color(0xFF_000000),
        onSurface = Color(0xFF_eeeeee),
        surfaceVariant = Color(0xFF_4a4a4a),
        onSurfaceVariant = Color(0xFF_d9e6e1),
        outline = Color(0xFF_a5b3ae),
    )
    val typo = Typo(
        body = TextStyle.Default.copy(color = color.onSurface, fontWeight = FontWeight.Normal)
    )
}
data class ColorScheme(
    val primary: Color,
    val onPrimary: Color,
    val primaryContainer: Color,
    val onPrimaryContainer: Color,
    val surface: Color,
    val onSurface: Color,
    val surfaceVariant: Color,
    val onSurfaceVariant: Color,
    val outline: Color,
)
data class Typo(
    val body: TextStyle,
    val smallTitle: TextStyle = body.copy(fontWeight = FontWeight.Medium)
)