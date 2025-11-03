package com.inf251.basedatos

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BDSQLite (context: Context): SQLiteOpenHelper(context, "INF-251", null, 1){
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE alumno (
                codgen TEXT PRIMARY KEY,
                appatern TEXT,
                nombre TEXT,
                fechanac TEXT,
                nota REAL,
                notacurv REAL
            )
        """.trimIndent())
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS alumno")
        onCreate(db)
    }
    fun insertar(appatern: String, nombre:String, fechanac:String, nota: Double, notacurv:Double, codgen:String): Long{
        val db = writableDatabase
        val valores = ContentValues().apply {
            put("appatern", appatern)
            put("nombre", nombre)
            put("fechanac", fechanac)
            put("nota", nota)
            put("notacurv",notacurv)
            put("codgen",codgen)
        }
        return db.insert("alumno", null, valores)
    }
    fun consultar(codgen:String): Cursor{
        val db = readableDatabase
        return db.rawQuery("SELECT * FROM alumno WHERE codgen = ?", arrayOf(codgen))
    }
    fun listar(): Cursor{
        val db = readableDatabase
        return db.rawQuery("SELECT * FROM alumno", null)
    }
    fun modificar(appatern: String, nombre:String, fechanac:String, nota: Double, notacurv:Double, codgen:String): Int{
        val db = writableDatabase
        val valores = ContentValues().apply {
            put("appatern", appatern)
            put("nombre", nombre)
            put("fechanac", fechanac)
            put("nota", nota)
            put("notacurv",notacurv)
        }
        return db.update("alumno", valores, "codgen = ?", arrayOf(codgen))
    }
    fun eliminar(codgen:String):Int{
        val db=writableDatabase
        return db.delete("alumno","codgen = ?",arrayOf(codgen))
    }
}