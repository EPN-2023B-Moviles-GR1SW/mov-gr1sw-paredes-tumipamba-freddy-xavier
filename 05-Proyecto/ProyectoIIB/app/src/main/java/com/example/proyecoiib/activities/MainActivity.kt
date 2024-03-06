package com.example.proyecoiib.activities


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val places = getSampleTouristPlaces()

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = TouristAdapter(places)
    }

    private fun getSampleTouristPlaces(): List<TouristPlace> {
        // Agregar datos de lugares turísticos en Ecuador
        return listOf(
            TouristPlace("FLORESTA", "QUITO", "CIUDAD HERMOSA"),
            TouristPlace("FICOA", "AMBATO", "CIUDAD HERMOSA"),
            TouristPlace("LA LAGUNA", "LATACUNGA", "CIUDAD HERMOSA"),
            
        )
    }
}
