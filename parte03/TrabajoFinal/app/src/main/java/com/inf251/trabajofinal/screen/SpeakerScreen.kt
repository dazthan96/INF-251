package com.inf251.trabajofinal.screen

import android.Manifest
import android.content.Context
import android.media.AudioDeviceInfo
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
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
import androidx.compose.runtime.DisposableEffect
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
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.inf251.trabajofinal.R
import com.inf251.trabajofinal.components.ContentText
import com.inf251.trabajofinal.components.IconButtonColumn
import com.inf251.trabajofinal.components.TitleText
import com.inf251.trabajofinal.navigation.AppScreen
import java.lang.Exception

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun SpeakerScreen(navController: NavController){
    val context = LocalContext.current
    val audioManager = remember { context.getSystemService(Context.AUDIO_SERVICE) as AudioManager }

    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }
    var speakerActive by remember { mutableStateOf(false) }

    BackHandler {
        releasePlayer(mediaPlayer)
        mediaPlayer=null
    }
    DisposableEffect(Unit) {
        onDispose {
            releasePlayer(mediaPlayer)
            mediaPlayer=null
        }
    }

    Column (Modifier
        .fillMaxSize()
        .background(colorResource(R.color.cardColor))){
        Column(Modifier.fillMaxSize().padding(35.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            TitleText(
                text = "Estado del Altavoz",
                color = R.color.black
            )
            Spacer(Modifier.height(15.dp))
            Text(text = if(speakerActive)"Activado" else "Desactivado")
            Spacer(Modifier.height(15.dp))
            Icon(
                painter = painterResource(R.drawable.speaker),
                contentDescription = "",
                modifier = Modifier.size(250.dp),
                tint = if (speakerActive){
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
                    text = if (speakerActive)"Desactivar" else "Activar",
                    idIcon = R.drawable.speaker,
                    width = 150,
                    colorButton = R.color.colorEdit,
                    colorContent = R.color.white,
                    enabled = true
                ) {
                    if (!speakerActive) {
                        // Crear y arrancar sonido en altavoz
                        mediaPlayer = MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI).apply {
                            isLooping = true
                            val speakerDevice = getBuiltInSpeaker(audioManager)
                            if (speakerDevice != null) {
                                setPreferredDevice(speakerDevice) // forzar salida al altavoz
                            }
                            start()
                        }
                        speakerActive = true
                    } else {
                        // Detener sonido y liberar
                        releasePlayer(mediaPlayer)
                        mediaPlayer = null
                        speakerActive = false
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

// Busca el altavoz integrado entre los dispositivos de salida disponibles
fun getBuiltInSpeaker(audioManager: AudioManager): AudioDeviceInfo? {
    val outputs = audioManager.getDevices(AudioManager.GET_DEVICES_OUTPUTS)
    return outputs.firstOrNull { it.type == AudioDeviceInfo.TYPE_BUILTIN_SPEAKER }
}

// Verifica si el MediaPlayer está actualmente enrutado al altavoz
@RequiresApi(Build.VERSION_CODES.P)
fun isSpeakerCurrentlyRoutedToSpeaker(mediaPlayer: MediaPlayer?): Boolean {
    val routed = mediaPlayer?.routedDevice
    return routed?.type == AudioDeviceInfo.TYPE_BUILTIN_SPEAKER
}

// Libera el MediaPlayer y quita cualquier preferencia de dispositivo
@RequiresApi(Build.VERSION_CODES.P)
fun releasePlayer(mediaPlayer: MediaPlayer?) {
    mediaPlayer?.apply {
        // Quitamos preferencia para volver a valores normales del sistema
        setPreferredDevice(null)
        stop()
        release()
    }
}