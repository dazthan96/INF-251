package com.inf251.gestionnotas.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

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
        label = { Text(label, fontSize = 14.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp) },
        readOnly = readOnly,
        enabled = enable,
        keyboardOptions = KeyboardOptions(keyboardType = type)
    )
}

@Preview(showSystemUi = true)
@Composable
fun EditTextPreview(){
    var contenido = "hola mundo"
    Column (Modifier.fillMaxSize()){
        ReuseOutlineText(
            value = contenido,
            onValueChange = {contenido=it},
            label = "ejemplo",
            enable = true,
            readOnly = true,
            type = KeyboardType.Text
        )
    }
}
