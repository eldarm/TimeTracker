package com.eldar.timetracker

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView

class ActivityLogFiles : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_files)


        val files = ListFiles()
        if (files != null) {
            for (fl in files) {
                Log.i("Files", fl.name)
                findViewById<TableLayout>(R.id.tableFiles).addView(
                    newFileTableRow(this.baseContext, fl.name)
                )
            }
        }

        findViewById<Button>(R.id.buttonFListExit).setOnClickListener {
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

    newRow.addView(newTextBut(ctx, "Show"), 1)
    newRow.addView(newTextBut(ctx, "Del"), 2)
    //var newShow = ImageButton(ctx)
    //newShow.setImageResource(R.drawable.ic_menu_view)
    return newRow
}