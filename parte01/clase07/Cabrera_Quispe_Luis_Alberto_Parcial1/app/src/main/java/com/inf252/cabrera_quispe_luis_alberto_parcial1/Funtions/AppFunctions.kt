package com.inf252.cabrera_quispe_luis_alberto_parcial1.funtions

fun repetirVocales(palabra: String): String {
    val vocales = setOf('a', 'e', 'i', 'o', 'u', 'á', 'é', 'í', 'ó', 'ú')
    return palabra.map { letra ->
        if (letra.lowercaseChar() in vocales) "$letra$letra" else "$letra"
    }.joinToString("")
}