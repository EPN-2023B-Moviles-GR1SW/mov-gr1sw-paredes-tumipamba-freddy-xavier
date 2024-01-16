package com.example.examen01.entidad

class Pelicula(
    var id: Int?,
    var titulo: String,
    var director: String,
    var ano: Int,
    var precioEntrada: Double,
    var estado: String,
    var cine: Cine = Cine()
) {
    constructor() : this(null, "", "", 0, 0.0, "")

    override fun toString(): String {
        return "$titulo - $director"
    }
}