package com.example.registroalumnos

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.registroalumnos.BaseDatos.MiRegistroAlumnos
import com.example.registroalumnos.BaseDatos.alumnos
import com.example.registroalumnos.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : ActivityWhithMenus() {
    lateinit var binding: ActivityMainBinding
    lateinit var elementos: MutableList<alumnos>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        elementos = ArrayList()

        binding.BAnadir.setOnClickListener {

            if (binding.ENombre.text.toString().isEmpty() || binding.EApellido.text.toString().isEmpty() || binding.ECurso.text.toString().isEmpty())
            {
                Toast.makeText(this, "Esta campo vacio", Toast.LENGTH_SHORT).show()
            }
            else{
                addElemento(alumnos(nombre = binding.ENombre.text.toString(), apellido = binding.EApellido.text.toString(), curso = binding.ECurso.text.toString()))

                Toast.makeText(this, "Se ha insertado el alumno correctamente", Toast.LENGTH_SHORT).show()

                // Limpiar foco
                clearFocus()

                // Ocultar teclado
                hideKeyboard()
            }

        }
    }

    private fun addElemento(elemento: alumnos) {
        CoroutineScope(Dispatchers.IO).launch {
            // Llamamos al metodo addElemento() del DAO para realizar la inserción
            MiRegistroAlumnos.database.AlumnosDAO().addElemento(elemento)
        }
    }

        //Al pulsar sobre el boton añadir, se limpia
        fun clearFocus() {
            binding.ENombre.setText("")
            binding.EApellido.setText("")
            binding.ECurso.setText("")
        }

        //Oculta el teclado cuando terminamos de escribir en el cuadro de texto
        fun Context.hideKeyboard() {
            val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(binding.ENombre.windowToken, 0)
            inputMethodManager.hideSoftInputFromWindow(binding.EApellido.windowToken, 1)
            inputMethodManager.hideSoftInputFromWindow(binding.ECurso.windowToken, 2)
        }

}
