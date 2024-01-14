import controllers.Cines
import models.Pelicula
import models.Cine
import java.io.File
import java.time.LocalDate
import java.time.Year
import java.util.*
fun main() {
    val rutaArchivo = "src/main/kotlin/files/cines.json"
    val archivoCines = File(rutaArchivo)
    val gestorCines = Cines(archivoCines)
    val scanner = Scanner(System.`in`)

    while (true) {
        println("\nBienvenido a la Gestión de Cines")
        println("1. Ver todos los cines")
        println("2. Agregar un nuevo cine")
        println("3. Actualizar un cine")
        println("4. Eliminar un cine")
        println("5. Agregar Película a un Cine")
        println("6. Mostrar Películas de un Cine")
        println("7. Actualizar Película de un Cine")
        println("8. Eliminar Película de un Cine")
        println("9. Salir")
        print("Por favor, ingrese el número de la opción que desea realizar: ")

        when (scanner.nextInt()) {
            1 -> {
                val cines = gestorCines.leerCines()
                println("\nCines disponibles:")
                cines.forEach {
                    if (it.estaAbierto) {
                        println(it)
                    }
                }
                pausar()
            }

            2 -> {
                println("\nIngrese el nombre del nuevo cine:")
                val nombre = scanner.next()
                println("Ingrese la ubicación del nuevo cine:")
                val ubicacion = scanner.next()
                println("Ingrese el número de empleados del nuevo cine:")
                val numeroEmpleados = scanner.nextInt()

                // Crear lista vacía de películas para el nuevo cine
                val listaPeliculas: MutableList<Pelicula> = mutableListOf()

                val nuevoCine =
                    Cine(nombre.uppercase(), ubicacion.uppercase(), true, numeroEmpleados, listaPeliculas)
                gestorCines.crearCine(nuevoCine)
                println("¡Cine agregado correctamente!")
                pausar()
            }

            3 -> {
                println("\nIngrese el nombre del cine que desea actualizar:")
                val nombreActualizar = scanner.next()
                val nombreUpperCase = nombreActualizar.uppercase()

                val cineExistente =
                    gestorCines.leerCines().find { it.nombre == nombreUpperCase }

                if (cineExistente != null) {
                    if (!cineExistente.estaAbierto) return
                    println("Ingrese la nueva ubicación del cine:")
                    val nuevaUbicacion = scanner.next()
                    println("Ingrese el nuevo número de empleados del cine:")
                    val nuevoNumeroEmpleados = scanner.nextInt()

                    val cineActualizado = Cine(
                        cineExistente.nombre,
                        nuevaUbicacion.uppercase(),
                        cineExistente.estaAbierto,
                        nuevoNumeroEmpleados,
                        cineExistente.listaPeliculas
                    )

                    gestorCines.actualizarCine(nombreUpperCase, cineActualizado)
                    println("¡Cine actualizado correctamente!")
                    pausar()
                } else {
                    println("El cine no existe.")
                    pausar()
                }
            }

            4 -> {
                println("\nIngrese el nombre del cine que desea eliminar:")
                val nombreActualizar = scanner.next()
                val nombreUpperCase = nombreActualizar.uppercase()

                val cineExistente =
                    gestorCines.leerCines().find { it.nombre == nombreUpperCase }

                if (cineExistente != null) {
                    if (!cineExistente.estaAbierto) return

                    val cineActualizado = Cine(
                        cineExistente.nombre,
                        cineExistente.ubicacion,
                        false,
                        cineExistente.numeroEmpleados,
                        cineExistente.listaPeliculas
                    )

                    gestorCines.actualizarCine(nombreUpperCase, cineActualizado)
                    println("¡Cine eliminado correctamente!")
                    pausar()
                } else {
                    println("El cine no existe.")
                    pausar()
                }
            }

            5 -> { // Agregar Película a un Cine
                println("\nIngrese el nombre del cine al que desea agregar una película:")
                val nombreCine = scanner.next()
                val nombreUpperCase = nombreCine.uppercase()

                val cine = gestorCines.leerCines()
                    .find { it.nombre == nombreUpperCase }

                if (cine != null && cine.estaAbierto) {
                    println("\nIngrese el título de la película:")
                    val titulo = scanner.next()
                    println("Ingrese el director de la película:")
                    val director = scanner.next()
                    println("Ingrese el año de la película:")
                    val año = scanner.nextInt()
                    println("Ingrese el precio de entrada de la película:")
                    val precioEntrada = scanner.nextDouble()
                    println("Ingrese el estado de la película (Estrenada - No Estrenada):")
                    val estado = scanner.next()

                    val nuevaPelicula = Pelicula(
                        titulo.uppercase(), director.uppercase(), año, precioEntrada, estado.uppercase()
                    )
                    cine.agregarPelicula(nuevaPelicula)
                    gestorCines.actualizarCine(nombreUpperCase, cine)
                    println("¡Película agregada al cine correctamente!")
                    pausar()
                } else {
                    println("El cine no existe.")
                    pausar()
                }
            }

            6 -> { // Mostrar Películas de un Cine
                println("\nIngrese el nombre del cine del que desea ver las películas:")
                val nombreCine = scanner.next()
                val nombreUpperCase = nombreCine.uppercase()

                val cine = gestorCines.leerCines()
                    .find { it.nombre == nombreUpperCase }

                if (cine != null && cine.estaAbierto) {
                    val peliculasCine = cine.obtenerPeliculas()
                    println("\nPelículas del cine $nombreUpperCase:")
                    peliculasCine.forEach { println(it) }
                    pausar()
                } else {
                    println("El cine no existe.")
                    pausar()
                }
            }

            7 -> { // Actualizar Película de un Cine
                println("\nIngrese el nombre del cine al que pertenece la película a actualizar:")
                val nombreCine = scanner.next()
                val nombreUpperCase = nombreCine.uppercase()

                val cine = gestorCines.leerCines()
                    .find { it.nombre == nombreUpperCase }

                if (cine != null && cine.estaAbierto) {
                    println("\nIngrese el título de la película que desea actualizar:")
                    val tituloPelicula = scanner.next()
                    val tituloPeliculaUpperCase = tituloPelicula.uppercase()

                    val peliculasCine = cine.obtenerPeliculas()
                    val pelicula = peliculasCine.find { it.titulo == tituloPeliculaUpperCase }
                    if (pelicula != null) {
                        println("Ingrese el nuevo precio de entrada de la película:")
                        val precioEntrada = scanner.nextDouble()

                        val peliculaActualizada = Pelicula(
                            pelicula.titulo, pelicula.director, pelicula.año, precioEntrada, pelicula.estado
                        )
                        cine.actualizarPelicula(tituloPeliculaUpperCase, peliculaActualizada)
                        gestorCines.actualizarCine(nombreUpperCase, cine)
                        println("¡Película actualizada correctamente!")
                        pausar()
                    } else {
                        println("Índice de película inválido.")
                        pausar()
                    }
                } else {
                    println("El cine no existe.")
                    pausar()
                }
            }

            8 -> { // Eliminar Película de un Cine
                println("\nIngrese el nombre del cine al que pertenece la película a eliminar:")
                val nombreCine = scanner.next()
                val nombreUpperCase = nombreCine.uppercase()

                val cine = gestorCines.leerCines()
                    .find { it.nombre == nombreUpperCase }

                if (cine != null && cine.estaAbierto) {
                    println("\nIngrese el título de la película que desea eliminar:")
                    val tituloPelicula = scanner.next()
                    val tituloPeliculaUpperCase = tituloPelicula.uppercase()

                    val peliculasCine = cine.obtenerPeliculas()
                    val pelicula = peliculasCine.find { it.titulo == tituloPeliculaUpperCase }
                    if (pelicula != null) {
                        cine.eliminarPelicula(tituloPeliculaUpperCase)
                        gestorCines.actualizarCine(nombreUpperCase, cine)
                        println("¡Película eliminada correctamente!")
                        pausar()
                    } else {
                        println("Índice de película inválido.")
                        pausar()
                    }
                } else {
                    println("El cine no existe.")
                    pausar()
                }
            }

            9 -> {
                println("Saliendo de la aplicación...")
                return
            }

            else -> {
                println("Opción inválida, por favor ingrese un número válido.")
                pausar()
            }
        }
    }
}

fun pausar() {
    println("\nPresiona Enter para continuar...")
    readLine()
}
