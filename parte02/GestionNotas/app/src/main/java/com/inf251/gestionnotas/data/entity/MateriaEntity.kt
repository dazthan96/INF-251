package com.inf251.gestionnotas.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "materia")
data class MateriaEntity(
    @PrimaryKey val siglaMat:String,
    @ColumnInfo(name = "nombreMat") val materia:String?,
    @ColumnInfo(name = "carreraMat") val carreraMat:String?,
    @ColumnInfo(name = "mencionMat") val mencionMat:String?
)
