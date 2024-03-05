package com.example.examen01.bd

import com.example.examen01.entidad.Cine
import com.example.examen01.entidad.Pelicula

class BaseDeDatos {

    companion object {
        var listaDeCines: ArrayList<Cine> = arrayListOf()
        var listaDePeliculas: ArrayList<Pelicula> = arrayListOf()

        init {
            listaDePeliculas.add(
                Pelicula(
                    0,
                    "Inception",
                    "Christopher Nolan",
                    2010,
                    12.5,
                    "Estrenada"
                )
            )
            listaDePeliculas.add(
                Pelicula(
                    1,
                    "Parasite",
                    "Bong Joon-ho",
                    2019,
                    11.0,
                    "Estrenada"
                )
            )
            listaDePeliculas.add(
                Pelicula(
                    2,
                    "Spiderman",
                    "Sam Raimi",
                    2002,
                    12.0,
                    "Estrenada"
                )
            )

            listaDeCines.add(
                Cine(
                    0,
                    "Multicines",
                    "Quito",
                    true,
                    12
                )
            )
            listaDeCines.add(
                Cine(
                    1,
                    "Supercines",
                    "Ambato",
                    true,
                    15
                )
            )
            listaDeCines.get(0).listaPeliculas.add(listaDePeliculas.get(0))
            listaDeCines.get(0).listaPeliculas.add(listaDePeliculas.get(1))
            listaDeCines.get(1).listaPeliculas.add(listaDePeliculas.get(2))
            listaDePeliculas.get(0).cine = listaDeCines.get(0)
            listaDePeliculas.get(1).cine = listaDeCines.get(0)
            listaDePeliculas.get(2).cine = listaDeCines.get(1)
        }
    }
}