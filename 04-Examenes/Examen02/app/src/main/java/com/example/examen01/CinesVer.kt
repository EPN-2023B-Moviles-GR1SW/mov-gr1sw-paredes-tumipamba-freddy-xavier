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
import androidx.appcompat.app.AlertDialog
import com.example.examen01.dao.CineDAO
import com.example.examen01.entidad.Cine
import com.google.android.material.snackbar.Snackbar

class CinesVer : AppCompatActivity() {
    val arregloCines = CineDAO().getAll()
    var posicionItemSeleccionado = 0
    var idCineSeleccionado = 0
    lateinit var listView: ListView
    lateinit var adaptador: ArrayAdapter<Cine>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cine_ver)
        listView = findViewById<ListView>(R.id.lv_cine_ver)
        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arregloCines
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val botonCrearCine = findViewById<Button>(R.id.btn_crear_cine)
        botonCrearCine.setOnClickListener {
            crearCine()
        }

        registerForContextMenu(listView)
    }

    fun crearCine() {
        val cine = Cine(
            null,
            "Nuevo Cine",
            "Ubicación",
            true,
            0
        )
        CineDAO().create(cine)
        adaptador.notifyDataSetChanged()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_cine, menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        posicionItemSeleccionado = posicion

        val cineSeleccionado = arregloCines.get(posicion)
        idCineSeleccionado = cineSeleccionado.id!!
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar_cine -> {
                irActividadConId(CineEditar::class.java, idCineSeleccionado)
                return true
            }

            R.id.mi_eliminar_cine -> {
                abrirDialogo()
                return true
            }

            R.id.mi_ver_peliculas -> {
                irActividadConId(PeliculasVer::class.java, idCineSeleccionado)
                return true
            }

            else -> super.onContextItemSelected(item)
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
            findViewById(R.id.id_layout_cine_ver),
            texto, Snackbar.LENGTH_LONG
        )
        snack.show()
    }

    fun abrirDialogo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Desea eliminar?")
        builder.setPositiveButton("Aceptar") { _, _ ->
            CineDAO().deleteById(idCineSeleccionado)
            mostrarSnackbar("Elemento id:$idCineSeleccionado eliminado")
            adaptador.notifyDataSetChanged()
        }
        builder.setNegativeButton("Cancelar", null)
        val dialogo = builder.create()
        dialogo.show()
    }

    override fun onResume() {
        super.onResume()
        adaptador.notifyDataSetChanged()
    }
}