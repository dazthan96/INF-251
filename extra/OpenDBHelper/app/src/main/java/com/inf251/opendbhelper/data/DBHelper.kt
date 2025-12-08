package com.inf251.opendbhelper.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.inf251.opendbhelper.model.Persona
class DBHelper (context: Context): SQLiteOpenHelper(context, "personas", null, 1){
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE personas(" +
                    "ci INTEGER PRIMARY KEY," +
                    "nombre TEXT," +
                    "apellido TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS personas")
        onCreate(db)
    }

    // CREATE
    fun insertarPersona(persona: Persona): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("ci", persona.ci)
            put("nombre", persona.nombre)
            put("apellido", persona.apellido)
        }
        return db.insert("personas", null, values)
    }

    // READ uno
    fun obtenerPersona(ci: Int): Persona? {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM personas WHERE ci = ?", arrayOf(ci.toString()))
        return if (cursor.moveToFirst()) {
            val persona = Persona(
                cursor.getInt(cursor.getColumnIndexOrThrow("ci")),
                cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                cursor.getString(cursor.getColumnIndexOrThrow("apellido"))
            )
            cursor.close()
            persona
        } else {
            cursor.close()
            null
        }
    }

    // READ todos
    fun obtenerTodasPersonas(): List<Persona> {
        val lista = mutableListOf<Persona>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM personas", null)
        if (cursor.moveToFirst()) {
            do {
                lista.add(
                    Persona(
                        cursor.getInt(cursor.getColumnIndexOrThrow("ci")),
                        cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                        cursor.getString(cursor.getColumnIndexOrThrow("apellido"))
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        return lista
    }

    // UPDATE
    fun actualizarPersona(persona: Persona): Int {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("nombre", persona.nombre)
            put("apellido", persona.apellido)
        }
        return db.update("personas", values, "ci = ?", arrayOf(persona.ci.toString()))
    }

    // DELETE
    fun eliminarPersona(ci: Int): Int {
        val db = writableDatabase
        return db.delete("personas", "ci = ?", arrayOf(ci.toString()))
    }
}