package com.inf251.gestionnotas.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index


@Entity(
    tableName = "asignacion",
    primaryKeys = ["ciDoc", "sigMat", "idSem"],
    foreignKeys = [
        ForeignKey(entity = DocenteEntity::class, parentColumns = ["carnetDoc"], childColumns = ["ciDoc"]),
        ForeignKey(entity = MateriaEntity::class, parentColumns = ["siglaMat"], childColumns = ["sigMat"]),
        ForeignKey(entity = SemestreEntity::class, parentColumns = ["idSemestre"], childColumns = ["idSem"])
    ],
    indices = [
        Index(value = ["ciDoc"]),
        Index(value=["sigMat"]),
        Index(value=["idSem"])
    ]
)
data class AsignacionEntity(
    val ciDoc:String,
    val sigMat:String,
    val idSem:String
)
