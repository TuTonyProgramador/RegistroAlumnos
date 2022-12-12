package com.example.registroalumnos

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.registroalumnos.BaseDatos.MiRegistroAlumnos
import com.example.registroalumnos.BaseDatos.alumnos
import com.example.registroalumnos.databinding.ActivityDeleteBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DeleteActivity : ActivityWhithMenus() {
    lateinit var binding: ActivityDeleteBinding
    lateinit var elementos: MutableList<alumnos>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        elementos = ArrayList()

        binding.BEliminar.setOnClickListener {
            if (binding.IENAlumno.text.toString().isEmpty())
            {
                Toast.makeText(this, "Este campo vacio", Toast.LENGTH_SHORT).show()
            }
            else{
                CoroutineScope(Dispatchers.IO).launch {
                    var eliminarAlumnos: alumnos

                    eliminarAlumnos = MiRegistroAlumnos.database.AlumnosDAO().obteneralumnopornombre(binding.IENAlumno.text.toString())

                    eliminarAlumnos.nombre = binding.IENAlumno.text.toString()

                    MiRegistroAlumnos.database.AlumnosDAO().deleteAlumnos(eliminarAlumnos)
                }
            }
            Toast.makeText(this, "Se ha eliminado el alumno correctamente", Toast.LENGTH_SHORT).show()

            // Limpiar foco
            clearFocus()

            // Ocultar teclado
            hideKeyboard()

            //IENAlumno
        }

    }

    //Al pulsar sobre el boton añadir, se limpia
    fun clearFocus() {
        binding.IENAlumno.setText("")
    }

    //Oculta el teclado cuando terminamos de escribir en el cuadro de texto
    fun Context.hideKeyboard() {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.IENAlumno.windowToken, 0)
    }
}