package com.example.musicplayer

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.musicplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        extracted()

        initClickListeners()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun extracted() {
        mediaPlayer = MediaPlayer()

        mediaPlayer.setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MOVIE)
                .setUsage(AudioAttributes.USAGE_MEDIA).build()
        )

        val path = "android.resource://$packageName/raw/movie"
        mediaPlayer.setDataSource(this, Uri.parse(path))
        mediaPlayer.prepare()
    }

    private fun initClickListeners() {

        binding.startBtn.setOnClickListener {
            if (mediaPlayer == null) {
                extracted()
            } else {
                mediaPlayer.start()
            }
        }

        binding.stopBtn.setOnClickListener {
            mediaPlayer.stop()
        }

        binding.pauseBtn.setOnClickListener {
            mediaPlayer.pause()
        }
    }
}