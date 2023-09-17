package com.eldar.timetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup

class ActivityAdd : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        findViewById<Button>(R.id.buttonAddAdd).setOnClickListener {
            var c = when (findViewById<RadioGroup>(R.id.radioGroupColor).checkedRadioButtonId) {
                R.id.radioButtonSky -> PastelColor.SKY
                R.id.radioButtonGrass -> PastelColor.GRASS
                R.id.radioButtonYolk -> PastelColor.YOLK
                R.id.radioButtonKiss -> PastelColor.KISS
                else -> PastelColor.SYSTEM_REST
            }
            ActivitiesList.Add(findViewById<EditText>(R.id.editTextText).text.toString(), c)
            finish()
        }

        findViewById<Button>(R.id.buttonCancelAdd).setOnClickListener {
            finish()
        }
    }
}