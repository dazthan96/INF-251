package com.inf252.roomdb.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.inf252.roomdb.data.entity.StudentEntity
@Dao
interface StudentDao {
    @Query("SELECT * FROM alumno")
    fun listar(): List<StudentEntity>

    @Query("SELECT * FROM alumno WHERE codgen LIKE :codGen")
    fun buscar(codGen: String) : StudentEntity

    @Insert
    fun insertar(student: StudentEntity)
    @Update
    fun modificar(student: StudentEntity)

    @Query("DELETE FROM alumno WHERE codgen LIKE :codGen")
    fun eliminar(codGen:String)
}