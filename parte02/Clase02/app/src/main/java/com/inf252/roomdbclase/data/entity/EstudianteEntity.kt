package com.inf252.roomdbclase.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alumno")
data class EstudianteEntity(
    @PrimaryKey val ci:String,
    @ColumnInfo(name="paterno") val paterno:String?,
    @ColumnInfo(name="materno") val materno:String?,
    @ColumnInfo(name="nombre") val nombre:String?,
    @ColumnInfo(name = "notaFinal") val nota:Double?,

)
