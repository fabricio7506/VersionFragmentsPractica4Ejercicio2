/**
 * Descripción: Actividad principal que maneja los Fragments del reproductor de música
 * Autor: Assistant
 * Fecha creación: 24/09/2024
 * Fecha última modificación: 24/09/2024
 */
// Descripción: Interacción con Imágenes versión Fragments.
// Autor: Fabricio Gabriel Carrazco Arana
// Fecha de creación: 21/09/2024
// Última modificación: 24/09/2024
package com.example.seagradece2

import android.app.Activity
import android.os.Bundle

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            showSongSelectionFragment()
        }
    }

    private fun showSongSelectionFragment() {
        fragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, SongSelectionFragment())
            .commit()
    }

    fun showPlayerFragment(selectedAudio: String) {
        fragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, PlayerFragment.newInstance(selectedAudio))
            .addToBackStack(null)
            .commit()
    }
}