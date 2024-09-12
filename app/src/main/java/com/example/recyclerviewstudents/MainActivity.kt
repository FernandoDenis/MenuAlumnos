package com.example.recyclerviewstudents

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewstudents.adapter.AlumnoAdapter
import com.example.recyclerviewstudents.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: AlumnoAdapter  // Referencia al adaptador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        // Variable para recibir extras del Intent
        val parExtra = intent.extras
        val msje = parExtra?.getString("mensaje")
        val nombr = parExtra?.getString("nombre")
        val cuent = parExtra?.getString("cuenta")
        val corre = parExtra?.getString("correo")
        val imag = parExtra?.getString("imagen")

        // Si se recibe un nuevo alumno
        if(msje == "nuevo"){
            val insertIndex: Int = AlumnoProvider.AlumnoList.count()
            AlumnoProvider.AlumnoList.add(insertIndex,
                Alumno(
                    alumno = nombr ?: "",
                    cuenta = cuent ?: "",
                    correo = corre ?: "",
                    imagen = imag ?: ""
                )
            )
            // Notificar al adaptador sobre el nuevo elemento
            adapter.notifyItemInserted(insertIndex)
        }

        // Click en el FloatingActionButton para ir a la actividad de agregar alumno
        binding.fabAddAlumno.setOnClickListener {
            val intento1 = Intent(this, MainActivityNuevo::class.java)
            startActivity(intento1)
        }
    }

    // Inicialización del RecyclerView y adaptador
    private fun initRecyclerView(){
        val manager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this, manager.orientation)
        binding.recyclerAlumnos.layoutManager = manager
        adapter = AlumnoAdapter(AlumnoProvider.AlumnoList) { alumno ->
            onItemSelected(alumno)
        }
        binding.recyclerAlumnos.adapter = adapter
        binding.recyclerAlumnos.addItemDecoration(decoration)
    }

    // Función para manejar la selección de un alumno
    private fun onItemSelected(alumno: Alumno){
        Toast.makeText(this, alumno.alumno, Toast.LENGTH_SHORT).show()
    }
}
