package com.inf251.gestionnotas.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.inf251.gestionnotas.data.entity.SemestreEntityFTS

@Dao
interface SemestreFTS {
    @Query("SELECT * FROM semetre_fts WHERE semetre_fts MATCH :query")
    suspend fun buscarSemFTS(query:String):List<SemestreEntityFTS>
}