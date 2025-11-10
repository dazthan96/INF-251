package com.inf252.roomdb.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.inf252.roomdb.data.dao.StudentDao
import com.inf252.roomdb.data.entity.StudentEntity

@Database(entities = [StudentEntity::class], version =1)
abstract class AlumnoDB: RoomDatabase() {
    abstract fun studentDao(): StudentDao
}