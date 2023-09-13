package com.eldar.timetracker

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    private var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ActivitiesList.ReadActivities(this.baseContext)
        //val path = context.getFilesDir()
        toolbar = findViewById<Toolbar>(R.id.materialToolbar);
        setSupportActionBar(toolbar);
        toolbar!!.inflateMenu(R.menu.menu_toolbar);

        findViewById<FloatingActionButton>(R.id.buttonAdd).setOnClickListener {
            Toast.makeText(this@MainActivity, "You clicked Add.", Toast.LENGTH_SHORT).show()

            startActivity(Intent(this, ActivityAdd::class.java))
        }
    }

    private fun makeActivityButton(t: String): Button {
        val newButton = Button(this)
        newButton.text = t
        if (t == ActivitiesList.restLabel) {
            newButton.hint = getString(R.string.stop_tracking)
        }
        newButton.setOnClickListener {
            Toast.makeText(
                applicationContext,
                "click on " + t,
                Toast.LENGTH_LONG
            ).show()
        }
        if (t != ActivitiesList.restLabel) {
            newButton.setOnLongClickListener {
                Toast.makeText(
                    applicationContext,
                    "long click on " + t,
                    Toast.LENGTH_LONG
                ).show();

                // findViewById<EditText>(R.id.textNameToDel).setText(t)
                val intent =
                    Intent(this, ActivityDel::class.java) // Intent(this, ActivityDel::class.java)
                intent.putExtra("ActivityToDel", t)
                startActivity(intent)

                Toast.makeText(
                    applicationContext,
                    "Confirming deleting $t",
                    Toast.LENGTH_LONG
                ).show();
                false
            }
        }
        return newButton
    }

    override fun onResume() {
        super.onResume()

        var v = findViewById<LinearLayout>(R.id.layoutActivities)
        v.removeAllViews()
        for (t in ActivitiesList.iter()) {
            v.addView(makeActivityButton(t), v.childCount)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_day -> {
                Toast.makeText(applicationContext, "click on Daye", Toast.LENGTH_LONG).show()
                true
            }

            R.id.menu_edit -> {
                Toast.makeText(applicationContext, "click on Edit", Toast.LENGTH_LONG).show()
                return true
            }

            R.id.menu_week -> {
                Toast.makeText(applicationContext, "click on Week", Toast.LENGTH_LONG).show()
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}