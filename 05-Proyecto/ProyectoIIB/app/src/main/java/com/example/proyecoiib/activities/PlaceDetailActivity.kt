package com.example.proyecoiib.activities


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class PlaceDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_detail)

        // Obtener datos del intent (puedes pasar estos datos desde la actividad principal)
        val placeName = intent.getStringExtra("placeName")
        val placeLocation = intent.getStringExtra("placeLocation")
        val placeDescription = intent.getStringExtra("placeDescription")

        // Mostrar los datos en las vistas
        val nameTextView: TextView = findViewById(R.id.nameTextView)
        val locationTextView: TextView = findViewById(R.id.locationTextView)
        val descriptionTextView: TextView = findViewById(R.id.descriptionTextView)

        nameTextView.text = placeName
        locationTextView.text = placeLocation
        descriptionTextView.text = placeDescription
    }
}

