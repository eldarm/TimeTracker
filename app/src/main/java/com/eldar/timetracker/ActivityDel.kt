package com.eldar.timetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class ActivityDel : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_del)

        val activityToDel = intent.getStringExtra("ActivityToDel")
        findViewById<TextView>(R.id.textNameToDel).setText(activityToDel)

        findViewById<Button>(R.id.buttonDelDel).setOnClickListener {
            ActivitiesList.Del(findViewById<TextView>(R.id.textNameToDel).text.toString())
            finish()
        }

        findViewById<Button>(R.id.buttonDelCancel).setOnClickListener {
            finish()
        }
    }
}
