/**
 * Descripción: Fragment para la selección de canciones
 * Autor: Assistant
 * Fecha creación: 24/09/2024
 * Fecha última modificación: 24/09/2024
 */
// Descripción: Reproductor de Música versión Fragments.
// Autor: Fabricio Gabriel Carrazco Arana
// Fecha de creación: 21/09/2024
// Última modificación: 24/09/2024
package com.example.seagradece2

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner

class SongSelectionFragment : Fragment() {
    private lateinit var spinner: Spinner
    private lateinit var selectButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_song_selection, container, false)

        spinner = view.findViewById(R.id.audioSpinner)
        selectButton = view.findViewById(R.id.selectButton)

        val audios = arrayOf("Canción Regueton", "Balada", "Rock Clásico", "Cumbia", "Electrónica")
        val adapter = ArrayAdapter(activity, android.R.layout.simple_spinner_item, audios)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        selectButton.setOnClickListener {
            (activity as? MainActivity)?.showPlayerFragment(spinner.selectedItem.toString())
        }

        return view
    }
}