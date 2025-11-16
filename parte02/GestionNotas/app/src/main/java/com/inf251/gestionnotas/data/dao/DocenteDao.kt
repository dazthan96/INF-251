package com.inf251.gestionnotas.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query
import androidx.room.Update
import com.inf251.gestionnotas.data.entity.DocenteEntity

@Dao
interface DocenteDao {
    @Query("SELECT * FROM docente")
    suspend fun listarDocN():List<DocenteEntity>
    @Query("SELECT * from docente where docente.carnetDoc =:pcarnet")
    suspend fun buscarCarnetDoc(pcarnet:String): DocenteEntity?

    @Insert(onConflict =  IGNORE)
    suspend fun insertarDoc(docente: DocenteEntity)

    @Update
    suspend fun modificarDoc(docente: DocenteEntity)

    @Query("DELETE FROM docente WHERE carnetDoc Like :p_ci")
    suspend fun aliminarDoc(p_ci:String)
}