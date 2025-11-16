package com.inf251.gestionnotas.data.entity

import androidx.room.Entity
import androidx.room.Fts4

@Fts4(contentEntity = SemestreEntity::class)
@Entity(tableName = "semetre_fts")
data class SemestreEntityFTS(
    val idSemestre :String,
    val anio:String,
    val periodo:String
)
