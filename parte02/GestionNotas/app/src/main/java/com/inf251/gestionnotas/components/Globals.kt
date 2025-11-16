package com.inf251.gestionnotas.components

import java.util.Calendar

val fecha = Calendar.getInstance()
val mesFecha = fecha.get(Calendar.MONTH)+1
val anioFecha = fecha.get(Calendar.YEAR)
val carreras=listOf("informatica", "matematica", "fisica", "estadistica")
val menciones = listOf("general", "desarrollo de software","ciencias de datos","ciencias de la computacion", "informatica industrial", "ing en sistemas", "redes y itic", "seguridad de la informacion" )
val genero = listOf("hombre", "mujer")
val periodos= listOf("I","II")
fun semestreNow(mes:Int, anio:Int): String{
    return if(mes<7){
        "${anio}-I"
    }else{
        "${anio}-II"
    }
}
fun getAnioActual(): Int {
    return Calendar.getInstance().get(Calendar.YEAR)
}


val semestreActual=semestreNow(mesFecha,anioFecha)