package bakuen.wear.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Modifier.condition(condition: Boolean, modifier: Modifier) =
    if (condition) then(modifier) else this