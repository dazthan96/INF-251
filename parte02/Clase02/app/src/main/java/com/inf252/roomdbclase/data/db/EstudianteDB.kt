package com.inf252.roomdbclase.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.inf252.roomdbclase.data.dao.EstudiantesDao
import com.inf252.roomdbclase.data.entity.EstudianteEntity

@Database(entities =[EstudianteEntity::class], version = 1)
abstract class EstudianteDB: RoomDatabase() {
    abstract fun estudianteDao(): EstudiantesDao
}