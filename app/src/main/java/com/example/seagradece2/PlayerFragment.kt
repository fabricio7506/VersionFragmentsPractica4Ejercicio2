/**
 * Descripción: Fragment para reproducir la canción seleccionada
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
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.util.Log

class PlayerFragment : Fragment() {
    private lateinit var audioNameText: TextView
    private lateinit var playButton: Button
    private lateinit var pauseButton: Button
    private lateinit var stopButton: Button
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_player, container, false)

        audioNameText = view.findViewById(R.id.audioNameText)
        playButton = view.findViewById(R.id.playButton)
        pauseButton = view.findViewById(R.id.pauseButton)
        stopButton = view.findViewById(R.id.stopButton)

        val selectedAudio = arguments?.getString("selectedAudio")
        audioNameText.text = selectedAudio

        val audioResource = when(selectedAudio) {
            "Reggaeton" -> R.raw.reague_song
            "Balada" -> R.raw.ballad
            "Rock Clásico" -> R.raw.classic_rock
            "Cumbia" -> R.raw.cumbia_classic
            "Electrónica" -> R.raw.electronic
            else -> R.raw.default_song
        }

        try {
            mediaPlayer = MediaPlayer.create(activity, audioResource)
            mediaPlayer.setOnPreparedListener {
                Log.d("PlayerFragment", "MediaPlayer prepared successfully")
            }
            mediaPlayer.setOnErrorListener { mp, what, extra ->
                Log.e("PlayerFragment", "Error in MediaPlayer: what=$what, extra=$extra")
                true
            }
        } catch (e: Exception) {
            Log.e("PlayerFragment", "Error creating MediaPlayer", e)
        }

        playButton.setOnClickListener {
            try {
                mediaPlayer.start()
            } catch (e: Exception) {
                Log.e("PlayerFragment", "Error starting MediaPlayer", e)
            }
        }
        pauseButton.setOnClickListener { mediaPlayer.pause() }
        stopButton.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer.prepare()
        }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

    companion object {
        fun newInstance(selectedAudio: String): PlayerFragment {
            val fragment = PlayerFragment()
            val args = Bundle()
            args.putString("selectedAudio", selectedAudio)
            fragment.arguments = args
            return fragment
        }
    }
}