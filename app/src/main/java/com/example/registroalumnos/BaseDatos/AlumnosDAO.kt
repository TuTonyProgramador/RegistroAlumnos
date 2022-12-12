package com.example.registroalumnos.BaseDatos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AlumnosDAO {
    // Utilizacion del Select
    @Query("SELECT * FROM registro_alumnos")
    // Devuelve la lista con todos los elementos
    fun getAllElements(): MutableList<alumnos>

    @Insert
    fun addElemento(taskEntity: alumnos): Long

    @Query("SELECT * FROM registro_alumnos WHERE nombre LIKE :nombre")
    fun obteneralumnopornombre(nombre: String): alumnos

    @Update
    fun updateAlumnos(taskEntity: alumnos): Int

    @Delete
    fun deleteAlumnos(taskEntity: alumnos): Int

}