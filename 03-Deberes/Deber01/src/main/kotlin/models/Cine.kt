package models

data class Cine(
    val nombre: String,
    val ubicacion: String,
    val estaAbierto: Boolean,
    val numeroEmpleados: Int,
    val listaPeliculas: MutableList<Pelicula>
){
    fun agregarPelicula(pelicula: Pelicula) {
        listaPeliculas.add(pelicula)
    }

    fun obtenerPeliculas(): List<Pelicula> {
        return listaPeliculas.toList()
    }

    fun actualizarPelicula(titulo: String, peliculaActualizada: Pelicula) {
        val indicesToUpdate = mutableListOf<Int>()

        // Encuentra los índices de los elementos que cumplen la condición
        for (index in listaPeliculas.indices) {
            val pelicula = listaPeliculas[index]
            if (pelicula.titulo == titulo) {
                indicesToUpdate.add(index)
            }
        }

        // Actualiza los elementos de la lista según los índices encontrados
        for (index in indicesToUpdate) {
            listaPeliculas[index] = peliculaActualizada
        }
    }

    fun eliminarPelicula(titulo: String) {
        val peliculasAEliminar = mutableListOf<Pelicula>()

        listaPeliculas.forEach { pelicula ->
            if (pelicula.titulo == titulo) {
                peliculasAEliminar.add(pelicula)
            }
        }

        listaPeliculas.removeAll(peliculasAEliminar)
    }
}

