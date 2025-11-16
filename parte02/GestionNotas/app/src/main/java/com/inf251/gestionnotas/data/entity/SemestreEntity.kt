package com.inf251.gestionnotas.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "semestre")
data class SemestreEntity(
    @PrimaryKey val idSemestre:String,
    @ColumnInfo(name = "anio") val anioSem:String,
    @ColumnInfo(name = "periodo") val periodoSem: String
)
