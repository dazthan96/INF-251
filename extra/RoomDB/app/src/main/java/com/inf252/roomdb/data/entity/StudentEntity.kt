package com.inf252.roomdb.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alumno")
data class StudentEntity (
    @PrimaryKey val codgen:String,
    @ColumnInfo(name="appatern") val apPatern:String?,
    @ColumnInfo(name = "nombre") val nombre:String?,
    @ColumnInfo(name = "fechanac") val fechaNac:String?,
    @ColumnInfo("nota") val nota:Double?,
    @ColumnInfo("notacurv") val notaCurv: Double?
)