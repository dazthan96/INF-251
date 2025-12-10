package com.inf251.trabajofinal.screen

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresPermission
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import com.inf251.trabajofinal.R
import com.inf251.trabajofinal.components.IconButtonColumn
import com.inf251.trabajofinal.components.TitleText
import com.inf251.trabajofinal.navigation.AppScreen
import kotlinx.coroutines.awaitCancellation

@Composable
fun BTScreen(navController: NavController){
    val context = LocalContext.current
    var btOn by remember { mutableStateOf(isBTEnabled()) }
    val permissionLauncher = rememberLauncherForActivityResult (
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            if (granted) {
                // Si se otorgó, refrescamos estado
                btOn = isBTEnabled()
            }
        }
    )
    LaunchedEffect(Unit) {
        val receiver = object : BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                if(intent?.action == BluetoothAdapter.ACTION_STATE_CHANGED){
                    val state = intent.getIntExtra(
                        BluetoothAdapter.EXTRA_STATE,
                        BluetoothAdapter.ERROR
                    )
                    btOn = state == BluetoothAdapter.STATE_ON
                }
            }
        }
        context.registerReceiver(receiver, IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED))
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
                text = "Estado del BlueTooth",
                color = R.color.black
            )
            Spacer(Modifier.height(15.dp))
            Text(text = if(btOn)"Activado" else "Desactivado")
            Spacer(Modifier.height(15.dp))
            Icon(
                painter = painterResource(R.drawable.bluetooth),
                contentDescription = "",
                modifier = Modifier.size(250.dp),
                tint = if (btOn){
                    colorResource(R.color.colorInfo)
                }else{
                    colorResource(R.color.disabledcontent)
                }
            )
            Spacer(Modifier.height(15.dp))
            Row (
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly){

                IconButtonColumn(
                    text = if (btOn)"Desactivar" else "Activar",
                    idIcon = R.drawable.bluetooth,
                    width = 150,
                    colorButton = R.color.colorEdit,
                    colorContent = R.color.white,
                    enabled = true
                ) {
                    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.S) {
                        // ✅ Android 9 o menor: se puede cambiar directamente
                        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.S){
                            if(ActivityCompat.checkSelfPermission(
                                context,
                                    Manifest.permission.BLUETOOTH_CONNECT
                            )!= PackageManager.PERMISSION_GRANTED
                            ){
                                permissionLauncher.launch(Manifest.permission.BLUETOOTH_CONNECT)
                                return@IconButtonColumn
                            }
                        }
                        setBTEnabled(!btOn)
                        btOn=isBTEnabled()
                    } else {
                        // ✅ Android 10 o mayor: abrir panel de Wi‑Fi
                        val intent = Intent(Settings.ACTION_BLUETOOTH_SETTINGS)
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

fun isBTEnabled(): Boolean{
    val adapter = BluetoothAdapter.getDefaultAdapter()
    return adapter?.isEnabled?:false
}
@RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
@Suppress("DEPRECATION")
fun setBTEnabled(enabled:Boolean){
    val adapter = BluetoothAdapter.getDefaultAdapter()
    if (enabled) adapter?.enable()else adapter?.disable()
}