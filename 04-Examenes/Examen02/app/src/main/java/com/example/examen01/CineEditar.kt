package com.example.examen01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import com.example.examen01.dao.CineDAO
import com.example.examen01.dao.PeliculaDAO
import com.google.android.material.snackbar.Snackbar

class CineEditar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cine_editar)

        // Recupera el ID
        val intent = intent
        val id = intent.getIntExtra("id", 1)
        // Buscar Pelicula
        val cine = CineDAO().getById(id)!!

        // Setear el texto en componentes visuales
        val nombre = findViewById<EditText>(R.id.input_nombre)
        val ubicacion = findViewById<EditText>(R.id.input_ubicacion)
        val numeroEmpleados = findViewById<EditText>(R.id.input_n_empleados)
        val isOpen = findViewById<Switch>(R.id.input_esta_abierto)

        nombre.setText(cine.nombre)
        ubicacion.setText(cine.ubicacion)
        numeroEmpleados.setText(cine.numeroEmpleados.toString())
        isOpen.isChecked = (cine.isOpen)

        val botonActualizar = findViewById<Button>(R.id.btn_actualizar_cine)
        botonActualizar.setOnClickListener {
            cine.nombre = nombre.text.toString()
            cine.ubicacion = ubicacion.text.toString()
            cine.numeroEmpleados = numeroEmpleados.text.toString().toInt()
            cine.isOpen = (isOpen.isChecked)

            CineDAO().update(cine)
            mostrarSnackbar("Cine Actualizada")
        }
        val botonVolver = findViewById<Button>(R.id.btn_volver)
        botonVolver.setOnClickListener {
            finish()
        }
    }

    fun mostrarSnackbar(texto: String) {
        val snack = Snackbar.make(
            findViewById(R.id.id_layout_cine_editar),
            texto, Snackbar.LENGTH_LONG
        )
        snack.show()
    }
}