import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.window.Window
import com.sdk.novelty.App
import kotlinx.browser.window
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
    onWasmReady {
        Window(
            title = "Novelty"
        ) {
            App(
                isDark = isSystemInDarkTheme()
            )
        }
    }
}