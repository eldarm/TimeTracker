package com.eldar.timetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class ActivityStats : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)

        val period = intent.getStringExtra("Period")
        findViewById<TextView>(R.id.textStatsHeader).setText(period)
        findViewById<TextView>(R.id.textStats).setText(StatsStr())

        findViewById<Button>(R.id.buttonStatsCancel).setOnClickListener {
            finish()
        }

        findViewById<Button>(R.id.buttonStatsEmail).setOnClickListener {
            Toast.makeText(applicationContext, "Email is not implemented yet.", Toast.LENGTH_LONG)
                .show()
            finish()
        }

    }
}