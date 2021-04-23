package com.felipe.chronometerkotlin

import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import com.felipe.chronometerkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var running: Boolean = false
    var pausedValue: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startBtn.setOnClickListener {
            initChronometer()
        }

        binding.pauseBtn.setOnClickListener {
            pauseChronometer()
        }

        binding.resetBtn.setOnClickListener {
            resetChronometer()
        }
    }

    private fun initChronometer() {
        if (!running) {
            binding.chronometerDpl.base = SystemClock.elapsedRealtime() - pausedValue
            binding.chronometerDpl.start()
            running = true
        }
    }

    private fun pauseChronometer() {
        if (running) {
            binding.chronometerDpl.stop()
            pausedValue = SystemClock.elapsedRealtime() - binding.chronometerDpl.base
            running = false
        }
    }

    private fun resetChronometer() {
        if (!running) {
            binding.chronometerDpl.base = SystemClock.elapsedRealtime()
            pausedValue = 0
        }
    }
}