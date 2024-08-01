package inch.cg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import bakuen.lib.navigator.LocalNavigator
import bakuen.lib.navigator.NavHost
import bakuen.lib.navigator.currentOrThrow
import bakuen.wear.components.AutoSize
import bakuen.wear.components.Background
import bakuen.wear.components.Text
import inch.cg.screens.main.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AutoSize(designWidth = 360f) {
                Background(color = Color.Black, fillMaxSize = true) {
                    NavHost(initialScreen = { MainScreen() }) { currentScreen ->
                        Background(color = Color.Black, contentAlignment = Alignment.Center, fillMaxSize = true) {
                            currentScreen()
                        }
                    }
                }
            }
        }
    }
}