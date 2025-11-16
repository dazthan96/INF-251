package com.inf251.gestionnotas.data.entity

import androidx.room.Entity
import androidx.room.Fts4

@Fts4(
    contentEntity = MateriaEntity::class,
    tokenizer = "unicode61",
    tokenizerArgs = ["tokenchars=-"]
)
@Entity(tableName = "materia_fts")
data class MateriaEntityFTS(
    val siglaMat:String,
    val nombreMat:String?,
    val carreraMat:String?,
    val mencionMat:String?
)
