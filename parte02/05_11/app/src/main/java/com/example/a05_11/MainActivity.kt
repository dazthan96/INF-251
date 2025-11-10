@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.a05_11

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.*
import androidx.room.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

// --- ENTIDAD ESTUDIANTE ---
@Entity
data class Estudiante(
    @PrimaryKey val ci: Int, // CI como clave primaria
    val paterno: String,
    val materno: String,
    val nombre: String,
    val notaFinal: Double
)

// --- DAO ---
@Dao
interface EstudianteDao {
    @Query("SELECT * FROM Estudiante ORDER BY notaFinal DESC")
    fun getAll(): Flow<List<Estudiante>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(est: Estudiante)

    @Delete
    fun delete(est: Estudiante)
}

// --- BASE DE DATOS INF251 ---
@Database(entities = [Estudiante::class], version = 1)
abstract class Inf251Db : RoomDatabase() {
    abstract fun estudianteDao(): EstudianteDao
}

// --- VIEWMODEL ---
class EstudianteViewModel(app: Application) : AndroidViewModel(app) {
    private val db = Room.databaseBuilder(app, Inf251Db::class.java, "INF251.db").build()
    private val dao = db.estudianteDao()

    val estudiantes = dao.getAll().stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    fun agregar(ci: Int, paterno: String, materno: String, nombre: String, nota: Double) =
        viewModelScope.launch(Dispatchers.IO) { 
            dao.insert(Estudiante(ci, paterno, materno, nombre, nota))
        }

    fun eliminar(est: Estudiante) =
        viewModelScope.launch(Dispatchers.IO) {
            dao.delete(est)
        }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vm = ViewModelProvider(this)[EstudianteViewModel::class.java]
        setContent { MaterialTheme { PantallaEstudiantes(vm) } }
    }
}

@Composable
fun PantallaEstudiantes(vm: EstudianteViewModel) {
    val lista by vm.estudiantes.collectAsState()
    var ci by remember { mutableStateOf("") }
    var paterno by remember { mutableStateOf("") }
    var materno by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var nota by remember { mutableStateOf("") }

    Column(Modifier.padding(16.dp)) {
        OutlinedTextField(ci, { ci = it }, label = { Text("CI") })
        OutlinedTextField(paterno, { paterno = it }, label = { Text("Paterno") })
        OutlinedTextField(materno, { materno = it }, label = { Text("Materno") })
        OutlinedTextField(nombre, { nombre = it }, label = { Text("Nombre") })
        OutlinedTextField(nota, { nota = it }, label = { Text("Nota Final") })

        Button(onClick = {
            val ciInt = ci.toIntOrNull()
            val notaDouble = nota.toDoubleOrNull()
            if (ciInt != null && notaDouble != null) {
                vm.agregar(ciInt, paterno, materno, nombre, notaDouble)
                ci = ""; paterno = ""; materno = ""; nombre = ""; nota = ""
            }
        }, Modifier.padding(vertical = 8.dp)) {
            Text("Agregar / Actualizar")
        }

        LazyColumn {
            items(lista) { est ->
                Row(
                    Modifier.fillMaxWidth().padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("${est.ci} - ${est.nombre} ${est.paterno} ${est.materno}")
                        Text("Nota Final: ${est.notaFinal}")
                    }
                    IconButton(onClick = { vm.eliminar(est) }) {
                        Icon(Icons.Default.Delete, contentDescription = "Eliminar")
                    }
                }
            }
        }
    }
}