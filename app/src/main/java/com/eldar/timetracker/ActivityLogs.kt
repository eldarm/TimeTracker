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
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ActivityLogs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logs)


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
}


fun newTextBut(ctx: Context, l: String) : View {
    var tv = Button(ctx)
    tv.setText(/* text = */ l)
    return tv
}

fun newFileTableRow(ctx: Context, fl: String) : TableRow {
    var newRow = TableRow(ctx)


    var newText = TextView(ctx)
    newText.setText(fl)
    newRow.addView(newText,0)

    newRow.addView(newTextBut(ctx, "\uD83D\uDC41"), 1) // Show 👁
    newRow.addView(newTextBut(ctx, "\uD83D\uDDD1"), 2) // Del 🗑
    newRow.addView(newTextBut(ctx, "✉"), 3) // Email ✉

    return newRow
}