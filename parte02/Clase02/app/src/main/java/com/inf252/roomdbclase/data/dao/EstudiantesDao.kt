package com.inf252.roomdbclase.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.inf252.roomdbclase.data.db.EstudianteDB
import com.inf252.roomdbclase.data.entity.EstudianteEntity
@Dao
interface EstudiantesDao {
    @Query("SELECT * FROM alumno")
    fun listar():List<EstudianteEntity>

    @Query("SELECT * FROM alumno WHERE ci ==:pci")
    fun buscar(pci:String): EstudianteEntity

    @Insert
    fun insertar(estudiante: EstudianteEntity)

    @Update
    fun modificar(estudiante: EstudianteEntity)

    @Query("DELETE FROM alumno WHERE ci==:pci")
    fun eliminar(pci:String)
}