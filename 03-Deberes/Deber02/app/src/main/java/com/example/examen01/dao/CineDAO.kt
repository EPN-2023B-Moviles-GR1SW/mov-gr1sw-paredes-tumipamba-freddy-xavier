package com.example.examen01.dao

import com.example.examen01.bd.BaseDeDatos
import com.example.examen01.entidad.Cine
import com.example.examen01.entidad.Pelicula

class CineDAO {

    fun getById(id: Int): Cine? {
        var cineEncontrado: Cine? = null
        getAll().forEach { cine: Cine ->
            if (cine.id == id) cineEncontrado = cine
        }
        return cineEncontrado
    }

    fun getAll(): ArrayList<Cine> {
        return BaseDeDatos.listaDeCines
    }

    fun create(cine: Cine) {
        val listaCines = getAll()
        if (listaCines.isEmpty()) {
            cine.id = 0
        } else {
            cine.id = listaCines.last().id?.plus(1)!!
        }
        listaCines.add(cine)
    }

    fun update(cineActualizado: Cine) {
        val listaCines = getAll()
        listaCines.forEachIndexed { index, cine ->
            if (cine.id == cineActualizado.id) {
                listaCines[index] = cineActualizado
                return
            }
        }
    }

    fun deleteById(id: Int): Boolean {
        val peliculaDAO = PeliculaDAO()
        getAll().forEach { cine: Cine ->
            if (cine.id == id) {
                cine.listaPeliculas.forEach { pelicula: Pelicula ->
                    peliculaDAO.deleteById(pelicula.id!!)
                }
            }
        }
        return getAll().removeIf { cine -> (cine.id == id) }
    }
}
