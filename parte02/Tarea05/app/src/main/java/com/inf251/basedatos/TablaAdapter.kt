package com.inf251.basedatos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class TablaAdapter (context: Context, private val datos:List<List<String>>): ArrayAdapter<List<String>>(context,R.layout.item_table,datos){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView?: LayoutInflater.from(context).inflate(R.layout.item_table,parent, false)
        val fila = datos[position]
        view.findViewById<TextView>(R.id.tvCodgen).text = fila[0]
        view.findViewById<TextView>(R.id.tvAppatern).text = fila[1]
        view.findViewById<TextView>(R.id.tvNombre).text = fila[2]
        view.findViewById<TextView>(R.id.tvFechanac).text = fila[3]
        view.findViewById<TextView>(R.id.tvNota).text = fila[4]
        view.findViewById<TextView>(R.id.tvNotacurv).text = fila[5]
        return view
    }
}