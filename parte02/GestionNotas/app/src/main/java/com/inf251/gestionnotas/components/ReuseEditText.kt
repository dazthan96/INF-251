package com.inf251.gestionnotas.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.inf251.gestionnotas.R

@Composable
fun ReuseOutlineText(
    value:String,
    onValueChange:(String)->Unit,
    label:String,
    enable: Boolean,
    readOnly:Boolean,
    type: KeyboardType){
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(0.75f),
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, fontSize = 14.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp, modifier = Modifier.padding(horizontal = 6.dp)) },
        readOnly = readOnly,
        enabled = enable,
        keyboardOptions = KeyboardOptions(keyboardType = type)
    )
}
@Composable
fun ReuseOutlineTextShort(
    value:String,
    onValueChange:(String)->Unit,
    label:String,
    size:Float,
    type: KeyboardType){
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(size),
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, fontSize = 14.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp, modifier = Modifier.padding(horizontal = 6.dp)) },
        readOnly = false,
        enabled = true,
        keyboardOptions = KeyboardOptions(keyboardType = type)
    )
}

@Preview(showSystemUi = true)
@Composable
fun EditTextPreview(){
    var contenido by remember { mutableStateOf("") }
    var escoger by remember { mutableStateOf("") }
    val items = listOf("manzana","pera", "naranjanaranjanaranjanaranjanaranja")
    Column (Modifier.fillMaxSize()){
        ReuseOutlineText(
            value = escoger,
            onValueChange = {escoger=it},
            label = "ejemplo",
            enable = true,
            readOnly = true,
            type = KeyboardType.Text
        )

        ReuseDropList(items,escoger,"Elija su genero") { escoger = it }
        ReuseOutlineTextShort(contenido, {contenido=it}, "contenido", 0.5f,KeyboardType.Text)
        ReuseDropListShort(items, contenido,"frutas",0.75f){contenido=it}
    }
}

@Composable
fun ReuseDropList(
    options: List<String>,
    selectedOption: String,
    text:String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box (Modifier.fillMaxWidth(0.75f)){
        OutlinedButton (
            modifier = Modifier.fillMaxWidth().height(55.dp),
            onClick = { expanded = !expanded },
            shape = RoundedCornerShape(10),

            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent,contentColor = Color.Black)

        ) {
            Text(
                selectedOption.ifEmpty { text },
                color = Color.Black,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start)
            Icon(
                painter = painterResource(
                    if (expanded) R.drawable.left2 else R.drawable.down
                ),

                contentDescription = null
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth(0.50f)
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option, maxLines = Int.MAX_VALUE) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}
@Composable
fun ReuseDropListShort(
    options: List<String>,
    selectedOption: String,
    text:String,
    size:Float,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box (Modifier.fillMaxWidth(size)){
        OutlinedButton (
            modifier = Modifier.fillMaxWidth(),
            onClick = { expanded = !expanded },
            shape = RoundedCornerShape(10),

            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent,contentColor = Color.Black)

        ) {
            Text(
                selectedOption.ifEmpty { text },
                color = Color.Black,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start)
            Icon(
                painter = painterResource(
                    if (expanded) R.drawable.left2 else R.drawable.down
                ),

                contentDescription = null
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}
