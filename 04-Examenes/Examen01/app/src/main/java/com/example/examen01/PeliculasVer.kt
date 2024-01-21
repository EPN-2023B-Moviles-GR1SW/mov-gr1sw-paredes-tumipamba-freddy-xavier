package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.examen01.dao.CineDAO
import com.example.examen01.dao.PeliculaDAO
import com.example.examen01.entidad.Cine
import com.example.examen01.entidad.Pelicula
import com.google.android.material.snackbar.Snackbar

class PeliculasVer : AppCompatActivity() {

    var arregloPeliculas = arrayListOf<Pelicula>()
    var cine: Cine = Cine()
    var posicionItemSeleccionado = 0
    var idPeliculaSeleccionada = 0
    lateinit var listView: ListView
    lateinit var adaptador: ArrayAdapter<Pelicula>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_peliculas_ver)
        // Recupera el ID
        val intent = intent
        val id = intent.getIntExtra("id", 1)
        // Buscar Películas
        cine = CineDAO().getById(id)!!
        arregloPeliculas = cine.listaPeliculas

        val nombreCine = findViewById<TextView>(R.id.tv_nombre_cine)
        nombreCine.text = "${cine.nombre}"

        listView = findViewById<ListView>(R.id.lv_pelicula_ver)
        adaptador = ArrayAdapter(
            this, // Contexto
            android.R.layout.simple_list_item_1, // cómo se va a ver (XML)
            arregloPeliculas
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val botonCrearPelicula = findViewById<Button>(R.id.btn_crear_pelicula)
        botonCrearPelicula.setOnClickListener {
            crearPelicula()
        }

        registerForContextMenu(listView)
    }
    fun crearPelicula() {
        val pelicula = Pelicula(
            null,
            "Nueva Película",
            "Director Desconocido",
            2022,
            10.0,
            "Estrenada",
            cine
        )
        PeliculaDAO().create(pelicula)
        adaptador.notifyDataSetChanged()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // Llenamos las opciones del menú
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_pelicula, menu)
        // Obtener el ID del ArrayListSeleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        posicionItemSeleccionado = posicion
        // Acceder al objeto Película en la posición seleccionada
        val peliculaSeleccionada = arregloPeliculas[posicion]
        // Obtener el ID de la Película seleccionada
        idPeliculaSeleccionada = peliculaSeleccionada.id!!
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar_pelicula -> {
                irActividadConId(PeliculaEditar::class.java, idPeliculaSeleccionada)
                return true
            }

            R.id.mi_eliminar_pelicula -> {
                abrirDialogo()
                return true
            }

            else -> super.onContextItemSelected(item)
        }
        val botonVolver = findViewById<Button>(R.id.btn_volver_4)
        botonVolver.setOnClickListener {
            val intent = Intent(this, CinesVer::class.java)
            startActivity(intent)
        }
    }


    fun irActividadConId(
        clase: Class<*>,
        id: Int
    ) {
        val intent = Intent(this, clase)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    fun mostrarSnackbar(texto: String) {
        val snack = Snackbar.make(
            findViewById(R.id.id_layout_pelicula_ver),
            texto, Snackbar.LENGTH_LONG
        )
        snack.show()
    }

    fun abrirDialogo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Desea eliminar?")
        builder.setPositiveButton(
            "Aceptar"
        ) { dialog, which ->
            PeliculaDAO().deleteById(idPeliculaSeleccionada)
            mostrarSnackbar("Elemento id:${idPeliculaSeleccionada} eliminado")
            adaptador.notifyDataSetChanged()
        }
        builder.setNegativeButton(
            "Cancelar",
            null
        )

        val dialogo = builder.create()
        dialogo.show()
    }

    override fun onResume() {
        super.onResume()
        adaptador.notifyDataSetChanged()
    }
}