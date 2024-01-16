package com.example.examen01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import com.example.examen01.dao.PeliculaDAO
import com.google.android.material.snackbar.Snackbar

class PeliculaEditar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pelicula_editar)

        // Recupera el ID
        val intent = intent
        val id = intent.getIntExtra("id", 1)
        // Buscar Pelicula
        val pelicula = PeliculaDAO().getById(id)!!

        // Setear el texto en componentes visuales
        val titulo = findViewById<EditText>(R.id.input_pelicula)
        val director = findViewById<EditText>(R.id.input_director)
        val ano = findViewById<EditText>(R.id.input_ano)
        val precioEntrada = findViewById<EditText>(R.id.input_precio_entrada)
        val estado = findViewById<Switch>(R.id.input_estado)

        titulo.setText(pelicula.titulo)
        director.setText(pelicula.director)
        ano.setText(pelicula.ano.toString())
        precioEntrada.setText(pelicula.precioEntrada.toString())
        estado.isChecked = (pelicula.estado == "Estrenada")

        val botonActualizar = findViewById<Button>(R.id.btn_actualizar_pelicula)
        botonActualizar.setOnClickListener {
            pelicula.titulo = titulo.text.toString()
            pelicula.director = director.text.toString()
            pelicula.ano = ano.text.toString().toInt()
            pelicula.precioEntrada = precioEntrada.text.toString().toDouble()
            pelicula.estado = if (estado.isChecked) "Estrenada" else "No Estrenada"

            PeliculaDAO().update(pelicula)
            mostrarSnackbar("Pelicula Actualizada")
        }
    }

    fun mostrarSnackbar(texto: String) {
        val snack = Snackbar.make(
            findViewById(R.id.id_layout_pelicula_editar),
            texto, Snackbar.LENGTH_LONG
        )
        snack.show()
    }
}