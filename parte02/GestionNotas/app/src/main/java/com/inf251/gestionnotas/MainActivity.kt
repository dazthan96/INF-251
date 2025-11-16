package com.inf251.gestionnotas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.inf251.gestionnotas.data.db.PlantelDocenteDB
import com.inf251.gestionnotas.navigation.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val db = Room.databaseBuilder(
            applicationContext,
            PlantelDocenteDB::class.java,
            "plantelDocenteDB"
        ).build()
        setContent {
            AppNavigation(
                docenteDao = db.docenteDao(),
                docenteFTS = db.docentefts(),
                materiaDao = db.materiaDao(),
                materiaFTS = db.materiafts(),
                semestreDao = db.semestreDao(),
                semestreFTS = db.semestrefts(),
                asignacionDao = db.asignacionDao()
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    //AppNavigation()
}