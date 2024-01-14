package controllers
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import models.Cine
import java.io.File
class Cines(private val archivo: File) {
    private val objectMapper = jacksonObjectMapper()
    private val cines: MutableList<Cine> = mutableListOf()

    init {
        cargarCines()
    }

    private fun cargarCines() {
        if (archivo.exists()) {
            val cinesJson = archivo.readText()
            cines.addAll(objectMapper.readValue<List<Cine>>(cinesJson))
        }
    }

    fun crearCine(cine: Cine) {
        cines.add(cine)
        guardarCines()
    }

    fun leerCines(): List<Cine> {
        return cines.toList()
    }

    fun actualizarCine(nombre: String, cineActualizado: Cine) {
        val cineIndex = cines.indexOfFirst { it.nombre == nombre }
        if (cineIndex != -1) {
            cines[cineIndex] = cineActualizado
            guardarCines()
        } else {
            println("El cine no existe.")
        }
    }

    private fun guardarCines() {
        val cinesJson = objectMapper.writeValueAsString(cines)
        archivo.writeText(cinesJson)
    }
}