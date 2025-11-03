package com.inf251.basedatos

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var bd: BDSQLite
    private lateinit var txtPatern : EditText
    private lateinit var txtNombre: EditText
    private lateinit var txtFechaNac: EditText
    private lateinit var txtNota: EditText
    private lateinit var txtNotaCurv: EditText
    private lateinit var txtCodGen: EditText
    private lateinit var listview: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bd = BDSQLite(this)

        txtPatern = findViewById(R.id.ApPatern)
        txtNombre = findViewById(R.id.Nombre)
        txtFechaNac = findViewById(R.id.FechaNac)
        txtNota = findViewById(R.id.Nota)
        txtNotaCurv = findViewById(R.id.NotaCurv)
        txtCodGen = findViewById(R.id.CodGen)
        listview = findViewById(R.id.ListData)
    }
    fun adicion(view: View){
        val appatern = txtPatern.text.toString().trim()
        val nombre = txtNombre.text.toString().trim()
        val fechanac = txtFechaNac.text.toString().trim()
        val notatext = txtNota.text.toString().trim()
        if (appatern.isEmpty()||nombre.isEmpty()||fechanac.isEmpty()||notatext.isEmpty()){
            Toast.makeText(this, "Todos los campos deben estar llenos", Toast.LENGTH_SHORT).show()
            return
        }
        val nota = notatext.toDoubleOrNull()
        if (nota == null){
            Toast.makeText(this, "La nota deben ser un numero valido", Toast.LENGTH_SHORT).show()
            return
        }

        val codigo = fechanac.filter { it.isLetterOrDigit() }
        val codGen = "${appatern.firstOrNull()}${nombre.firstOrNull()}-${codigo.take(4)}${codigo.takeLast(2)}"
        txtCodGen.setText(codGen)
        val resultado = bd.insertar(appatern,nombre,fechanac,nota, notaCurva(nota.toInt()),codGen)
        if(resultado>0){
            Toast.makeText(this, "Registro insertado correctamente", Toast.LENGTH_SHORT).show()
            limpiar()
        } else{
            Toast.makeText(this, "Error al insertar el registro", Toast.LENGTH_SHORT).show()
        }
    }
    fun consultar(view: View){
        val codGen = txtCodGen.text.toString()
        val cursor = bd.consultar(codGen)
        if(cursor.moveToFirst()){
            txtPatern.setText(cursor.getString(cursor.getColumnIndexOrThrow("appatern")))
            txtNombre.setText(cursor.getString(cursor.getColumnIndexOrThrow("nombre")))
            txtFechaNac.setText(cursor.getString(cursor.getColumnIndexOrThrow("fechanac")))
            txtNota.setText(cursor.getDouble(cursor.getColumnIndexOrThrow("nota")).toString())
            txtNotaCurv.setText(cursor.getDouble(cursor.getColumnIndexOrThrow("notacurv")).toString())
        } else{
            Toast.makeText(this, "No encontrado", Toast.LENGTH_SHORT).show()
        }
        cursor.close()
    }
    fun listar(view: View){
        val cursor = bd.listar()
        val lista = mutableListOf<List<String>>()
        while (cursor.moveToNext()){
            val fila = listOf(
                cursor.getString(cursor.getColumnIndexOrThrow("codgen")),
                cursor.getString(cursor.getColumnIndexOrThrow("appatern")),
                cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                cursor.getString(cursor.getColumnIndexOrThrow("fechanac")),
                cursor.getDouble(cursor.getColumnIndexOrThrow("nota")).toString(),
                cursor.getDouble(cursor.getColumnIndexOrThrow("notacurv")).toString()
            )
            lista.add(fila)
        }
        val adapter = TablaAdapter(this, lista)
        listview.adapter = adapter
        cursor.close()
    }
    fun modificar(view: View){
        val appatern = txtPatern.text.toString()
        val nombre = txtNombre.text.toString()
        val fechanac = txtFechaNac.text.toString()
        val nota = txtNota.text.toString().toDouble()
        val notacurv = txtNotaCurv.text.toString().toDouble()
        val codgen = txtCodGen.text.toString()
        val resultado = bd.modificar(appatern,nombre,fechanac,nota,notacurv,codgen)
        Toast.makeText(this, if(resultado>0)"Modificado correctamente" else "Nose encontro el codigo",
            Toast.LENGTH_SHORT).show()
    }
    fun eliminar(view: View){
        val codgen = txtCodGen.text.toString()
        val resultado = bd.eliminar(codgen)
        Toast.makeText(this, if(resultado>0)"eliminado correctamente" else "no se encontro codigo",
            Toast.LENGTH_SHORT).show()
    }
    fun salir(view: View){
        finish()
    }
    private fun limpiar(){
        txtPatern.setText("")
        txtNombre.setText("")
        txtFechaNac.setText("")
        txtNota.setText("")
        txtNotaCurv.setText("")
        txtCodGen.setText("")
    }
    private fun notaCurva(number: Int): Double{
        val notaAux = number + (number*0.1)
        return if (notaAux>100){
            100.0
        }else{
            (notaAux)
        }
    }
}