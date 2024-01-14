package com.example.gr1_sw_fxpt

class BBaseDatosMemoria {
    companion object {
        val arregloBEntrenador = arrayListOf<BEntrenador>()
        init {
            arregloBEntrenador
                .add(
                    BEntrenador(1, "Adrian", "@a.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(2, "Vicente", "@b.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(3, "Carolina", "@c.com")
                )
        }
    }
}