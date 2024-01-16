package com.example.examen01.entidad

class Cine(
    var id: Int?,
    var nombre: String,
    var ubicacion: String,
    var isOpen: Boolean,
    var numeroEmpleados: Int,
    var listaPeliculas: ArrayList<Pelicula> = arrayListOf()
) {
    constructor() : this(null, "", "", false, 0)

    override fun toString(): String {
        return "$nombre"
    }
}