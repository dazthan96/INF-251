package com.inf251.gestionnotas.data.entity

import androidx.room.Entity
import androidx.room.Fts4

@Fts4(
    contentEntity = DocenteEntity::class,
    tokenizer = "unicode61",
    tokenizerArgs = ["tokenchars=-"]
)
@Entity(tableName = "docente_fts")
data class DocenteEntityFTS(
    val carnetDoc:String,
    val paternoDoc:String,
    val maternoDoc:String,
    val nombreDoc:String,
    val carreraDoc:String,
    val sexoDoc:String,

)
