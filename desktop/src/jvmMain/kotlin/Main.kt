import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.sdk.novelty.App

fun main() {
    application {
        Window(
            title = "Novelty",
            onCloseRequest = {
                //exitApplication()
            }
        ) {
            App(
                isDark = isSystemInDarkTheme()
            )
        }
    }
}