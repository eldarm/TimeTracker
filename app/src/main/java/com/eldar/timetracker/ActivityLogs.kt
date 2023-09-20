package com.eldar.timetracker

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ActivityLogs : AppCompatActivity() {
    var appCtx: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logs)
        appCtx = applicationContext

        val files = ListFiles()
        if (files != null) {
            for (fl in files) {
                Log.i("Files", fl.name)
                findViewById<TableLayout>(R.id.tableFiles).addView(
                    newFileTableRow(this.baseContext, fl.name)
                )
            }
        }

        findViewById<FloatingActionButton>(R.id.buttonListBack).setOnClickListener {
            finish()
        }
    }

    fun newTextBut(ctx: Context, l: String, oc: (ctx: Context, f: String) -> Unit): View {
        var tv = Button(ctx)
        tv.setText(/* text = */ l)
        if (oc != null) {
            tv.setOnClickListener { oc.invoke(this.appCtx!!, l) }
        }
        return tv
    }

    fun newFileTableRow(ctx: Context, fl: String): TableRow {
        var newRow = TableRow(ctx)


        var newText = TextView(ctx)
        newText.setText(fl)
        newRow.addView(newText, 0)

        // Show 👁
        newRow.addView(newTextBut(ctx, "\uD83D\uDC41") { ctx: Context, f: String ->
            run {
                Log.i("Files", "click on Show " + fl)
                Toast.makeText(ctx, "click on Show " + fl, Toast.LENGTH_LONG).show()
            }
        }, 1)
        // Del 🗑
        newRow.addView(newTextBut(ctx, "\uD83D\uDDD1") { ctx: Context, f: String ->
            run {
                Log.i("Files", "click on Del " + fl)
                Toast.makeText(ctx, "click on Del " + fl, Toast.LENGTH_LONG).show()
            }
        }, 2)
        // Email ✉
        newRow.addView(newTextBut(ctx, "✉") { ctx: Context, f: String ->
            run {
                Log.i("Files", "click on Email " + fl)
                Toast.makeText(ctx, "click on Email " + fl, Toast.LENGTH_LONG).show()
            }
        }, 3)

        return newRow
    }

}

