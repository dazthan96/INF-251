package com.inf252.roomdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.room.Room
import com.inf252.roomdb.data.dao.StudentDao
import com.inf252.roomdb.data.db.AlumnoDB
import com.inf252.roomdb.data.entity.StudentEntity

class MainActivity : ComponentActivity() {
    private lateinit var db: AlumnoDB
    private lateinit var dao: StudentDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = Room.databaseBuilder(
            applicationContext,
            AlumnoDB::class.java,
            "INF-251"
        ).allowMainThreadQueries().build()
        dao = db.studentDao()
        setContent {
            var appatern by remember { mutableStateOf("") }
            var nombre by remember { mutableStateOf("") }
            var fechanac by remember { mutableStateOf("") }
            var notaText by remember { mutableStateOf("0.0") }
            var estudiantes by remember { mutableStateOf(dao.listar()) }
            val codigo = fechanac.filter{it.isLetterOrDigit()}
            val nuevoCod = "${appatern.firstOrNull()?:'_'}${nombre.firstOrNull()?:'_'}-${codigo.take(4)}${codigo.takeLast(2)}"
            val codgen = if (nuevoCod.length <9) "Codigo" else nuevoCod
            val notaOriginal = notaText.toDoubleOrNull()?:0.0
            val notaCurvada = kotlin.math.min(notaOriginal*(1.1), 100.0)

            Column (Modifier.fillMaxWidth().padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally){

                Spacer(Modifier.height(10.dp))

                ReuseTextField(appatern,{appatern= it},"Paterno", KeyboardType.Text,true)
                Spacer(Modifier.height(10.dp))
                ReuseTextField(nombre, {nombre = it}, "Nombre", type = KeyboardType.Text,true)
                Spacer(Modifier.height(10.dp))
                ReuseTextField(fechanac, {fechanac=it}, "Fecha de Nacimiento dd/mm/yyyy", KeyboardType.Phone,true)
                Spacer(Modifier.height(10.dp))
                ReuseTextField(notaText, {notaText= it}, "Nota",KeyboardType.Number,true)
                Spacer(Modifier.height(10.dp))
                ReuseTextField("%.2f".format(notaCurvada),{},"Nota Curvada", KeyboardType.Text,false )
                Spacer(Modifier.height(10.dp))
                ReuseTextField(codgen, {},"Codigo Alumno", KeyboardType.Text, false)
                Spacer(Modifier.height(10.dp))
                Row (Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround){
                    IconButtonReuse(R.drawable.add,"añadir",
                        {
                            val estudiante = StudentEntity(codgen,appatern,nombre, fechanac,notaOriginal,notaCurvada)
                            dao.insertar(estudiante)
                            estudiantes=dao.listar()
                        },
                        Color.White,Color(76, 175, 80))
                    IconButtonReuse(R.drawable.search, "buscar",{},Color.White,Color(33, 150, 243))
                    IconButtonReuse(R.drawable.delete, "borrar", {},Color.White,Color(244, 67, 54))
                    IconButtonReuse(R.drawable.edit,"editar", {},Color.White,Color(255, 152, 0))
                    IconButtonReuse(R.drawable.list,"listar",{},Color.White,Color(63, 81, 181))
                    IconButtonReuse(R.drawable.exit,"salir",{finish()},Color.Black,Color(158, 158, 158))

                }
                Spacer(Modifier.height(5.dp))
                LazyColumn {
                    items(estudiantes){ estudiante->
                        Text("${estudiante.codgen} - ${estudiante.nombre} - ${estudiante.apPatern} - ${estudiante.nota} - ${estudiante.notaCurv} - ${estudiante.fechaNac}")
                    }
                }
            }
        }
    }
}

@Composable
fun ReuseTextField(
    value: String,
    onValueChange: (String)->Unit,
    label:String,
    type: KeyboardType,
    enabled: Boolean
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(0.75f),
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(keyboardType = type)

    )
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

@Preview(showBackground = true)
@Composable
fun ViewPreview() {
    var contenido = "hola"
    Column (Modifier.fillMaxWidth().fillMaxHeight()){
        ReuseTextField(
            value = contenido,
            onValueChange = {contenido = it},
            label = "correo electronido",
            KeyboardType.Text,
            true
        )
        Row (Modifier.fillMaxWidth().padding(5.dp)){
            IconButtonReuse(R.drawable.add, "Añadir",{},Color.White,Color.Blue
            )
        }
    }


}