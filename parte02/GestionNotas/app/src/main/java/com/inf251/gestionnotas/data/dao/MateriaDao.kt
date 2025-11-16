package com.inf251.gestionnotas.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query
import androidx.room.Update
import com.inf251.gestionnotas.data.entity.MateriaEntity

@Dao
interface MateriaDao {
    @Query("SELECT * FROM materia")
    suspend fun listarMatN():List<MateriaEntity>

    @Insert(onConflict =  IGNORE)
    suspend fun insertarMat(materia: MateriaEntity)

    @Update
    suspend fun modificarMat(materia: MateriaEntity)

    @Query("DELETE FROM materia WHERE siglaMat Like :p_sigla")
    suspend fun aliminarDoc(p_sigla:String)

    @Query("SELECT * FROM materia WHERE siglaMat LIKE :p_sigla")
    suspend fun buscarMatSingle(p_sigla:String): MateriaEntity?
}