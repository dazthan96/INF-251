package com.inf252.cabrera_quispe_luis_alberto_parcial1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.inf252.cabrera_quispe_luis_alberto_parcial1.navigation.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Image(
                painterResource(R.drawable.letras), contentDescription = ""
            )
            AppNavigation()
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PrevWork(){
    AppNavigation()
}
