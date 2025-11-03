package com.clase03.ejercicio2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column (
                Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally)
            {
                Spacer(Modifier.height(8.dp))
                CardImage(R.drawable.umsalogo,"logo umsa","Universidad Mayor de San Andres",Modifier.height(75.dp),Modifier.fillMaxWidth().height(85.dp))
                Box(Modifier.fillMaxWidth().height(8.dp).background(color=Color.Red))
                MainImage(R.drawable.umsacenso,"Imagen Promocional", Modifier.fillMaxWidth(0.75f))

                Spacer(Modifier.height(8.dp))
                Box(Modifier.background(color = Color.Red, shape = CircleShape).size(35.dp))
                Spacer(Modifier.height(16.dp))

                Row (Modifier.fillMaxWidth().padding(horizontal = 2.dp)){
                    CardImage(R.drawable.ofertaacademica,"","",Modifier.fillMaxWidth(),Modifier.weight(1f))
                    Spacer(Modifier.width(4.dp))
                    CardImage(R.drawable.serviciovirtual,"","",Modifier.fillMaxWidth(),Modifier.weight(1f))
                }

                Spacer(Modifier.height(4.dp))
                Row (Modifier.fillMaxWidth().padding(horizontal = 2.dp)){
                    CardImage(R.drawable.medioscom,"","",Modifier.fillMaxWidth(),Modifier.weight(1f))
                    Spacer(Modifier.width(4.dp))
                    CardImage(R.drawable.interacciosoc,"","",Modifier.fillMaxWidth(),Modifier.weight(1f))
                }
                Spacer(Modifier.height(2.dp))
                Row(Modifier.fillMaxWidth().padding(2.dp)){
                    CardImage(R.drawable.convocatoria,"","",Modifier.height(75.dp),Modifier.weight(1f))
                    Spacer(Modifier.width(2.dp))
                    CardImage(R.drawable.becaspre,"","",Modifier.height(75.dp),Modifier.weight(1f))
                    Spacer(Modifier.width(2.dp))
                    CardImage(R.drawable.concatoriadoc,"","",Modifier.height(75.dp),Modifier.weight(1f))
                    Spacer(Modifier.width(2.dp))
                    CardImage(R.drawable.convocatoriapost,"","",Modifier.height(75.dp),Modifier.weight(1f))
                    Spacer(Modifier.width(2.dp))
                    CardImage(R.drawable.pureumsa,"","",Modifier.height(75.dp),Modifier.weight(1f))
                    Spacer(Modifier.width(2.dp))
                    CardImage(R.drawable.scopusumsa,"","",Modifier.height(75.dp),Modifier.weight(1f))

                }
            }

        }
    }
}
@Composable
fun MainImage(idImage: Int, description: String, imageModifier: Modifier=Modifier ){
    val imageR: Painter = painterResource(idImage)
    Image(
        painter = imageR,
        contentDescription = description,
        modifier = imageModifier,
        contentScale = ContentScale.Crop
    )
}
@Composable
fun CardImage(idImage: Int, description: String, text:String, modifierImage: Modifier = Modifier,modifierBox: Modifier=Modifier ){
    val imageSub: Painter= painterResource(idImage)
    Box (modifier = modifierBox
        .background(color = Color(2,75,110))
        .padding(8.dp)
    ){
        Row (verticalAlignment = Alignment.CenterVertically){
            Image(
                painter = imageSub,
                contentDescription = description,
                modifier=modifierImage,
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.size(4.dp))
            Text(text=text, fontWeight = FontWeight.Bold, fontSize = 25.sp, color = Color.White)
        }
    }

}
@Preview
@Composable
fun PrevCard(){
    CardImage(R.drawable.umsalogo,"logo umsa","Universidad Mayor de San Andres",Modifier.height(50.dp),Modifier.fillMaxWidth().height(60.dp))
}