package com.eldar.timetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class ActivityAdd : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        findViewById<Button>(R.id.buttonAddAdd).setOnClickListener {
            ActivitiesList.Add(findViewById<EditText>(R.id.editTextText).text.toString())
            finish()
        }

        findViewById<Button>(R.id.buttonCancelAdd).setOnClickListener {
            finish()
        }
    }
}