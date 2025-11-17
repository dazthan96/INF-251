package com.inf251.gestionnotas.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.inf251.gestionnotas.data.entity.AsignacionEntity
import com.inf251.gestionnotas.data.pojo.DocMatSemPOJO

@Dao
interface AsignacionDao {
    @Insert
    suspend fun insertAsig(asignacion: AsignacionEntity)
    @Update
    suspend fun updateAsig(asignaicon: AsignacionEntity)
    @Query("DELETE FROM asignacion WHERE ciDoc = :ciDoc AND sigMat = :siglaMat AND idSem = :semestre")
    suspend fun deleteAsig(ciDoc: String, siglaMat: String, semestre: String)

    @Query("""
        SELECT d.carnetDoc as ciDocente,
        d.nombreDoc as nomDocente,
        d.paternoDoc as patDocente,
        d.maternoDoc as matDocente,
        d.sexoDoc as sexoDocente,
        m.siglaMat as sigMateria,
        m.nombreMat as nomMateria,
        m.mencionMat as menMateria,
        s.anio as anio,
        s.periodo as periodo,
        d.carreraDoc as carrera
        FROM asignacion a
        INNER JOIN docente d on a.ciDoc = d.carnetDoc
        INNER JOIN materia m on a.sigMat = m.siglaMat
        INNER JOIN semestre s on a.idSem = s.idSemestre
        WHERE (:ciDoc IS NULL OR d.carnetDoc = :ciDoc)
        AND (:nomDoc IS NULL OR d.nombreDoc = :nomDoc)
        AND (:patDoc IS NULL OR d.paternoDoc = :patDoc)
        AND (:matDoc IS NULL OR d.maternoDoc = :matDoc)
        AND(:sexoDoc IS NULL OR d.sexoDoc = :sexoDoc)
        AND(:sigMat IS NULL OR m.siglaMat = :sigMat)
        AND(:nomMat IS NULL OR m.nombreMat = :nomMat)
        AND(:menMat IS NULL OR m.mencionMat = :menMat)
        AND(:carrera IS NULL OR d.carreraDoc = :carrera)
        AND(:anioSem IS NULL OR s.anio = :anioSem)
        AND(:perSem IS NULL OR s.periodo = :perSem)
    """)
    suspend fun buscarGeneral(
        ciDoc:String?,
        nomDoc:String?,
        patDoc:String?,
        matDoc:String?,
        sexoDoc:String?,
        sigMat:String?,
        nomMat:String?,
        menMat:String?,
        carrera:String?,
        anioSem:String?,
        perSem:String?,

    ):List<DocMatSemPOJO>
}