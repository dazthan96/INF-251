package com.inf251.trabajofinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.inf251.trabajofinal.navigation.AppNavigation
import com.inf251.trabajofinal.screen.BTScreen
import com.inf251.trabajofinal.screen.CameraScreen
import com.inf251.trabajofinal.screen.MicScreen
import com.inf251.trabajofinal.screen.PantallaScreen
import com.inf251.trabajofinal.screen.SpeakerScreen
import com.inf251.trabajofinal.ui.theme.TrabajoFinalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavigation()
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TrabajoFinalTheme {
        Greeting("Android")
    }
}