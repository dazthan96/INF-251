package com.inf251.trabajofinal.screen

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Build
import android.provider.Settings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.awaitCancellation
import com.inf251.trabajofinal.R
import com.inf251.trabajofinal.components.Buttons
import com.inf251.trabajofinal.components.IconButtonColumn
import com.inf251.trabajofinal.components.TitleText
import com.inf251.trabajofinal.navigation.AppScreen

@Composable
fun WifiScreen(navController: NavController){
    val context = LocalContext.current
    var wifiEnabled by remember { mutableStateOf(isWifiOn(context)) }

    LaunchedEffect (Unit) {
        val receiver = object : BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                if(intent?.action == WifiManager.WIFI_STATE_CHANGED_ACTION){
                    val state = intent.getIntExtra(
                        WifiManager.EXTRA_WIFI_STATE,
                        WifiManager.WIFI_STATE_UNKNOWN
                    )
                    wifiEnabled = state == WifiManager.WIFI_STATE_ENABLED
                }
            }
        }
        context.registerReceiver(receiver, IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION))
        try {
            awaitCancellation()
        }finally {
            context.unregisterReceiver(receiver)
        }
    }
    Column (Modifier
        .fillMaxSize()
        .background(colorResource(R.color.cardColor))){
        Column(Modifier.fillMaxSize().padding(35.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            TitleText(
                text = "Estado del WIFI",
                color = R.color.black
            )
            Spacer(Modifier.height(15.dp))
            Text(text = if(wifiEnabled)"Activado" else "Desactivado")
            Spacer(Modifier.height(15.dp))
            Icon(
                painter = painterResource(R.drawable.wifi),
                contentDescription = "",
                modifier = Modifier.size(250.dp),
                tint = if (wifiEnabled){
                    colorResource(R.color.colorDelete)
                }else{
                    colorResource(R.color.disabledcontent)
                }
            )
            Spacer(Modifier.height(15.dp))
            Row (
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly){

                IconButtonColumn(
                    text = if (wifiEnabled)"Desactivar" else "Activar",
                    idIcon = R.drawable.wifi,
                    width = 150,
                    colorButton = R.color.colorEdit,
                    colorContent = R.color.white,
                    enabled = true
                ) {
                    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                        // ✅ Android 9 o menor: se puede cambiar directamente
                        setWifiEnabled(context, !wifiEnabled)
                        wifiEnabled = isWifiOn(context)
                    } else {
                        // ✅ Android 10 o mayor: abrir panel de Wi‑Fi
                        val intent = Intent(Settings.Panel.ACTION_WIFI)
                        context.startActivity(intent)
                    }
                }
                IconButtonColumn(
                    text = "Salir",
                    idIcon = R.drawable.home,
                    width = 150,
                    colorButton = R.color.colorView,
                    colorContent = R.color.white,
                    enabled = true
                ){
                    navController.navigate(AppScreen.FirstScreen.route)
                }
            }
        }

    }
}

@Suppress("DEPRECATION")
fun setWifiEnabled(context: Context, enabled: Boolean) {
    val wifiManager=context.getSystemService(Context.WIFI_SERVICE) as WifiManager
    wifiManager.isWifiEnabled=enabled
}
fun isWifiOn(context: Context): Boolean{
    val wm = context.getSystemService(Context.WIFI_SERVICE) as? WifiManager?:return false
    return wm.isWifiEnabled
}
