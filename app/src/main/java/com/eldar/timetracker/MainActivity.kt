package com.eldar.timetracker

import android.app.AlertDialog
import android.content.DialogInterface
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

        ActivitiesList.SetContext(this.baseContext)
        //val path = context.getFilesDir()
        toolbar = findViewById<Toolbar>(R.id.materialToolbar);
        setSupportActionBar(toolbar);
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setTitle("Main Page");
//        }
//        toolbar.setSubtitle("Test Subtitle");
        toolbar!!.inflateMenu(R.menu.menu_toolbar);

        findViewById<FloatingActionButton>(R.id.buttonAdd).setOnClickListener {
            Toast.makeText(this@MainActivity, "You clicked Add.", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, ActivityAdd::class.java)
            startActivity(intent)

//            val viewList = findViewById<LinearLayout>(R.id.listActivities)
//            val newButton = Button(this)
//            //newButton.text = "Qu: " + (seq++).toString() + "!"
//            seq++
//            newButton.text = "Qu: $seq!"
//            viewList.addView(newButton, viewList.size)
//            newButton.setOnClickListener {
//                Toast.makeText(
//                    this@MainActivity,
//                    "You clicked " + newButton.text,
//                    Toast.LENGTH_SHORT
//                ).show()
//            }

//            var v = findViewById<LinearLayout>(R.id.layoutActivities)
//            val c = v.childCount
//            ActivitiesList.Add("Qu: $c")
//
//            var seq = 0
//            for (t in ActivitiesList.iter()) {
//                if (seq++ >= v.childCount) {
//                    val newButton = Button(this)
//                    newButton.text = t
//                    v.addView(newButton, v.childCount)
//                    newButton.setOnClickListener {
//                        Toast.makeText(
//                            this@MainActivity,
//                            "You clicked " + newButton.text,
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                }
//            }
        }
    }

    private fun askYesNo(t: String): Boolean {
        // IMPORTANT: This does not really work on architectural level.
        // It does not wait for the response. So, you don't get the response really, unless
        // you put a hndler into on* functions. However, these function don't have access
        // to stuff you need, e.g. refresh the main screen list (not to mention that
        // it may be changed by the time, you get a user response.
        // I'm removing it and replacing with a separate activity which is much more reliable and
        // robust way to reach that result (confirmation of deletion).
        var confirmDelete = 0
        AlertDialog.Builder(this)
            .setTitle("Delete activity $t")
            .setMessage("Are you sure?")
            .setPositiveButton(
                "DELETE",
                DialogInterface.OnClickListener { dialog, which -> // Do nothing but close the dialog
                    confirmDelete = 1
                    Toast.makeText(
                        applicationContext,
                        "Confirmed",
                        Toast.LENGTH_LONG
                    ).show()
                    // dialog.dismiss()
                })
            .setNegativeButton(
                "CANCEL",
                DialogInterface.OnClickListener { dialog, which -> // Do nothing
                    confirmDelete = -1
                    Toast.makeText(
                        applicationContext,
                        "Cancelled",
                        Toast.LENGTH_LONG
                    ).show()
                   // dialog.dismiss()
                })
            .create().show()
        return confirmDelete > 0
    }

    private fun makeActivityButton(t: String): Button {
        val newButton = Button(this)
        newButton.text = t
        newButton.setOnClickListener {
            Toast.makeText(
                applicationContext,
                "click on " + t,
                Toast.LENGTH_LONG
            ).show()
        }
        newButton.setOnLongClickListener {
            Toast.makeText(
                applicationContext,
                "long click on " + t,
                Toast.LENGTH_LONG
            ).show();
            val c = askYesNo(t)
            Toast.makeText(
                applicationContext,
                "Confirming deleting $t:" + c.toString(),
                Toast.LENGTH_LONG
            ).show();
            false
        }
        return newButton
    }

    override fun onResume() {
        super.onResume()

        var v = findViewById<LinearLayout>(R.id.layoutActivities)
        v.removeAllViews()
        for (t in ActivitiesList.iter()) {
//            val newButton = Button(this)
//            newButton.text = t
//            v.addView(newButton, v.childCount)
            v.addView(makeActivityButton(t), v.childCount)
//            newButton.setOnClickListener {
//                Toast.makeText(
//                    this@MainActivity,
//                    "You clicked " + newButton.text,
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
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