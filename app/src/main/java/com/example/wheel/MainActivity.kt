package com.example.wheel

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wheel = findViewById<Wheel>(R.id.wheel_view)
        val applyButton = findViewById<Button>(R.id.apply_button)
        val progressEditText = findViewById<EditText>(R.id.progress_edit_text)

        applyButton.setOnClickListener {
            val progress = progressEditText.text.toString()
            if (progress.isNotEmpty() && progress.toInt() <= 360 && progress.toInt() >= 0) {
                val animation = WheelAnimation(wheel, progress.toFloat()).apply {
                    duration = 3000
                }
                wheel.startAnimation(animation)
            } else {
                Toast.makeText(this, getString(R.string.error_message), Toast.LENGTH_SHORT).show()
            }
        }
    }
}