package com.inf251.opendbhelper.repository

import com.inf251.opendbhelper.data.DBHelper
import com.inf251.opendbhelper.model.Persona

class PersonaRepository(private val dbHelper: DBHelper) {
    fun insertar(persona: Persona)=dbHelper.insertarPersona(persona)
    fun obtener(ci:Int) = dbHelper.obtenerPersona(ci)
    fun obtenerTodos()= dbHelper.obtenerTodasPersonas()
    fun actualizar(persona: Persona)=dbHelper.actualizarPersona(persona)
    fun eliminar(ci:Int)=dbHelper.eliminarPersona(ci)
}