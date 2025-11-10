package com.inf252.roomdbclase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.Room
import com.inf252.roomdbclase.data.dao.EstudiantesDao
import com.inf252.roomdbclase.data.db.EstudianteDB
import com.inf252.roomdbclase.ui.theme.RoomDBClaseTheme

class MainActivity : ComponentActivity() {
    private lateinit var db: EstudianteDB
    private lateinit var dao: EstudiantesDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = Room.databaseBuilder(
            applicationContext,
            EstudianteDB::class.java,
            "INF251"
        ).allowMainThreadQueries().build()
        dao = db.estudianteDao()
        setContent {
            var ci by remember { mutableStateOf("") }
            var paterno by remember { mutableStateOf("") }
            var materno by remember {mutableStateOf("")}
            var nombre by remember{mutableStateOf("")}
            var notafinal by remember { mutableStateOf("") }
            Column (Modifier.padding(16.dp, )){
                OutlinedTextField(ci, {ci = it}, label = {Text("CI")})
                OutlinedTextField(paterno, {paterno = it}, label = {Text("Paterno")})
                OutlinedTextField(materno, {materno = it}, label = {Text("Materno")})
                OutlinedTextField(nombre, {nombre = it}, label = {Text("Nombre")})
                OutlinedTextField(notafinal, {notafinal = it}, label = {Text("Nota")})

            }
            
        }
    }
}

@Composable
fun IconButtonReuse(
    icon: Int,
    contentDescription: String,
    onClick:()->Unit,
    tint: Color,
    background: Color
){
    IconButton(
        onClick = onClick,
        modifier=Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(background)
    ) {
        Icon(
            painterResource(icon),
            contentDescription=contentDescription,
            tint =tint
        )
    }
}

