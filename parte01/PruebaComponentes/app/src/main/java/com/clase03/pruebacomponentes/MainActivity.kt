package com.clase03.pruebacomponentes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.clase03.pruebacomponentes.navigation.AppNavigation
import com.clase03.pruebacomponentes.screens.FirstScreen
import com.clase03.pruebacomponentes.ui.theme.PruebaComponentesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PruebaComponentesTheme {
                Surface (color = MaterialTheme.colorScheme.background){
                    AppNavigation()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    PruebaComponentesTheme {
        AppNavigation()
    }
}