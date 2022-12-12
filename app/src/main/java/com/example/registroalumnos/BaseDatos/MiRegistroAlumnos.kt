package com.example.registroalumnos.BaseDatos

import android.app.Application
import androidx.room.Room

class MiRegistroAlumnos: Application() {
    companion object {
        // lateinit es que se va a inicializar mas tarde
        lateinit var database: DBAlumnos
    }
    override fun onCreate() {
        super.onCreate()
        MiRegistroAlumnos.database = Room.databaseBuilder(this,DBAlumnos::class.java, "DBAlumnos").build()
    }
}