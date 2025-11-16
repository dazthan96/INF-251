package com.inf251.gestionnotas.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.inf251.gestionnotas.data.dao.AsignacionDao
import com.inf251.gestionnotas.data.dao.DocenteDao
import com.inf251.gestionnotas.data.dao.DocenteFTS
import com.inf251.gestionnotas.data.dao.MateriaDao
import com.inf251.gestionnotas.data.dao.MateriaFTS
import com.inf251.gestionnotas.data.dao.SemestreDao
import com.inf251.gestionnotas.data.dao.SemestreFTS
import com.inf251.gestionnotas.data.entity.AsignacionEntity
import com.inf251.gestionnotas.data.entity.DocenteEntity
import com.inf251.gestionnotas.data.entity.DocenteEntityFTS
import com.inf251.gestionnotas.data.entity.MateriaEntity
import com.inf251.gestionnotas.data.entity.MateriaEntityFTS
import com.inf251.gestionnotas.data.entity.SemestreEntity
import com.inf251.gestionnotas.data.entity.SemestreEntityFTS

@Database(
    entities = [
        DocenteEntity::class, DocenteEntityFTS::class,
        MateriaEntity::class, MateriaEntityFTS::class,
        SemestreEntity::class, SemestreEntityFTS::class,
        AsignacionEntity::class]
    , version = 1,
    exportSchema = false)
abstract class PlantelDocenteDB: RoomDatabase(){
    abstract fun docenteDao(): DocenteDao
    abstract fun docentefts(): DocenteFTS
    abstract fun materiaDao(): MateriaDao
    abstract fun materiafts(): MateriaFTS
    abstract fun semestreDao(): SemestreDao
    abstract fun semestrefts(): SemestreFTS
    abstract fun asignacionDao(): AsignacionDao
}