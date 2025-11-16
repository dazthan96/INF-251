package com.inf251.gestionnotas.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.inf251.gestionnotas.data.entity.MateriaEntity

@Dao
interface MateriaFTS {
    @Query("""
        SELECT materia.*
        FROM materia 
        JOIN materia_fts on materia.siglaMat = materia_fts.siglaMat
        WHERE materia_fts MATCH :query""")
    suspend fun buscarMFts(query:String):List<MateriaEntity>
}