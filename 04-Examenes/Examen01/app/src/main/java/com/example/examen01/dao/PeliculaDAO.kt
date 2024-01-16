package com.example.examen01.dao

import com.example.examen01.bd.BaseDeDatos
import com.example.examen01.entidad.Pelicula

class PeliculaDAO {

    fun getById(id: Int): Pelicula? {
        var peliculaEncontrada: Pelicula? = null
        getAll().forEach { pelicula: Pelicula ->
            if (pelicula.id == id) peliculaEncontrada = pelicula
        }
        return peliculaEncontrada
    }

    fun getAll(): ArrayList<Pelicula> {
        return BaseDeDatos.listaDePeliculas
    }

    fun create(pelicula: Pelicula) {
        val listaPeliculas = getAll()
        if (listaPeliculas.isEmpty()) {
            pelicula.id = 0
        } else {
            pelicula.id = listaPeliculas.last().id?.plus(1)!!
        }
        listaPeliculas.add(pelicula)
        pelicula.cine.listaPeliculas.add(pelicula)
    }

    fun update(peliculaActualizada: Pelicula) {
        val listaPeliculas = getAll()
        listaPeliculas.forEachIndexed { index, pelicula ->
            if (pelicula.id == peliculaActualizada.id) {
                listaPeliculas[index] = peliculaActualizada
                return
            }
        }
    }

    fun deleteById(id: Int) {
        val pelicula = getById(id)
        if (pelicula != null) {
            pelicula.cine.listaPeliculas.remove(pelicula)
            getAll().remove(pelicula)
        }
    }

}