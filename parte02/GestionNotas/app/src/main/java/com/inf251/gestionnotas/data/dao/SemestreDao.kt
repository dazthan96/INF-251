package com.inf251.gestionnotas.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query
import androidx.room.Update
import com.inf251.gestionnotas.data.entity.SemestreEntity

@Dao
interface SemestreDao {
    @Query("SELECT * FROM semestre")
    suspend fun listarSemN(): List<SemestreEntity>

    @Insert(onConflict = IGNORE)
    suspend fun insertarSem(semestre: SemestreEntity)

    @Update
    suspend fun modificarMat(semestre: SemestreEntity)

    @Query("SELECT * FROM semestre WHERE idSemestre = :p_semestre")
    suspend fun buscarSemestre(p_semestre:String): SemestreEntity?

}