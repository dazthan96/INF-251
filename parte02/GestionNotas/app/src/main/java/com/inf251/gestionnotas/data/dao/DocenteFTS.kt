package com.inf251.gestionnotas.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.inf251.gestionnotas.data.entity.DocenteEntity

@Dao
interface DocenteFTS {
    @Query("""
        SELECT docente.*
        FROM docente 
        JOIN docente_fts on docente.carnetDoc = docente_fts.carnetDoc
        WHERE docente_fts MATCH :query""")
    suspend fun buscarDocFts(query:String):List<DocenteEntity>
}