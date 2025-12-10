package com.inf251.trabajofinal.screen

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.os.Build
import android.provider.MediaStore
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import com.inf251.trabajofinal.R
import com.inf251.trabajofinal.components.IconButtonColumn
import com.inf251.trabajofinal.components.TitleText
import com.inf251.trabajofinal.navigation.AppScreen
import java.lang.Exception

@SuppressLint("MissingPermission")
@Composable
fun MicScreen(navController: NavController){
    val context = LocalContext.current
    var micOn by remember { mutableStateOf(false) }
    var audioRecord: AudioRecord? by remember { mutableStateOf(null) }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            if (granted) {
                audioRecord = startAudioRecord()
                audioRecord?.startRecording()
                micOn = true
            } else {
                micOn = false
            }
        }
    )

    Column (Modifier
        .fillMaxSize()
        .background(colorResource(R.color.cardColor))){
        Column(Modifier.fillMaxSize().padding(35.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            TitleText(
                text = "Estado del Microfono",
                color = R.color.black
            )
            Spacer(Modifier.height(15.dp))
            Text(text = if(micOn)"Activado" else "Desactivado")
            Spacer(Modifier.height(15.dp))
            Icon(
                painter = painterResource(R.drawable.mic),
                contentDescription = "",
                modifier = Modifier.size(250.dp),
                tint = if (micOn){
                    colorResource(R.color.teal_200)
                }else{
                    colorResource(R.color.disabledcontent)
                }
            )
            Spacer(Modifier.height(15.dp))
            Row (
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly){

                IconButtonColumn(
                    text = if (micOn)"Desactivar" else "Activar",
                    idIcon = R.drawable.mic,
                    width = 150,
                    colorButton = R.color.colorEdit,
                    colorContent = R.color.white,
                    enabled = true
                ) {
                    if(!micOn){
                        permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
                    }else{
                        try {
                            audioRecord?.stop()
                            audioRecord?.release()
                        }catch (e: Exception){
                            e.printStackTrace()
                        }
                        audioRecord=null
                        micOn=false
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
@SuppressLint("MissingPermission")
fun startAudioRecord(): AudioRecord{
    val bufferSize = AudioRecord.getMinBufferSize(
        44100,
        AudioFormat.CHANNEL_IN_MONO,
        AudioFormat.ENCODING_PCM_16BIT
    )
    return AudioRecord(
        MediaRecorder.AudioSource.MIC,
        44100,
        AudioFormat.CHANNEL_IN_MONO,
        AudioFormat.ENCODING_PCM_16BIT,
        bufferSize
    )
}