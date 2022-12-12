package com.example.registroalumnos.BaseDatos

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [alumnos::class],
    version = 1
)

abstract class DBAlumnos : RoomDatabase(){
    abstract fun AlumnosDAO(): AlumnosDAO
}