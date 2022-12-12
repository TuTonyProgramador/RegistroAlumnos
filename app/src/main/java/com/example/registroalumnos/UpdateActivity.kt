package com.example.registroalumnos

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.registroalumnos.BaseDatos.MiRegistroAlumnos
import com.example.registroalumnos.BaseDatos.alumnos
import com.example.registroalumnos.databinding.ActivityUpdateBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class UpdateActivity: ActivityWhithMenus() {
    lateinit var binding: ActivityUpdateBinding
    lateinit var elementos: MutableList<alumnos>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        elementos = ArrayList()

        binding.BActualizar.setOnClickListener {
            if (binding.INNombreA.text.toString().isEmpty() || binding.INCurso.text.toString().isEmpty())
            {
                Toast.makeText(this, "Este campo vacio", Toast.LENGTH_SHORT).show()
            }
            else {
                CoroutineScope(Dispatchers.IO).launch {
                    var actualizarAlumnos: alumnos

                    actualizarAlumnos = MiRegistroAlumnos.database.AlumnosDAO().obteneralumnopornombre(binding.INNombreA.text.toString())

                    actualizarAlumnos.curso = binding.INCurso.text.toString()

                    MiRegistroAlumnos.database.AlumnosDAO().updateAlumnos(actualizarAlumnos)
                }

                Toast.makeText(this, "Se ha actualizado el alumno correctamente", Toast.LENGTH_SHORT).show()

                // Limpiar foco
                clearFocus()

                // Ocultar teclado
                hideKeyboard()
            }
        }
    }

    //Al pulsar sobre el boton añadir, se limpia
    fun clearFocus() {
        binding.INNombreA.setText("")
        binding.INCurso.setText("")
    }

    //Oculta el teclado cuando terminamos de escribir en el cuadro de texto
    fun Context.hideKeyboard() {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.INNombreA.windowToken, 0)
        inputMethodManager.hideSoftInputFromWindow(binding.INCurso.windowToken, 1)
    }
}
