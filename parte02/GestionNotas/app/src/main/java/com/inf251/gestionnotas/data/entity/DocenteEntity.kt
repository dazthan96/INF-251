package com.inf251.gestionnotas.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "docente")
data class DocenteEntity(
    @PrimaryKey val carnetDoc:String,
    @ColumnInfo(name = "paternoDoc") val paterno:String?,
    @ColumnInfo(name = "maternoDoc") val materno:String?,
    @ColumnInfo(name="nombreDoc") val nombre:String?,
    @ColumnInfo(name="carreraDoc") val carrera:String?,
    @ColumnInfo(name="sexoDoc") val sexo:String?
)
