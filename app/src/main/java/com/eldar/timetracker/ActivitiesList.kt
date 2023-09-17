package com.eldar.timetracker

import android.content.Context
import android.graphics.Color
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import android.util.Log
import java.text.SimpleDateFormat
import java.util.Calendar

data class ActivityItem(val a: String, val c: Int)

private val logTag = "ActivityList"
private var itemsFile: File? = null
private val itemsFileName = "items.tsv"
private val restItem = ActivityItem(ActivitiesList.restLabel, PastelColor.SYSTEM_REST)

class ActivitiesList {
    private var vs = mutableListOf(restItem)

    companion object {
        val restLabel = "REST"

        @JvmStatic
        private var list: ActivitiesList = ActivitiesList()

        /** Must be run before using the Activities list. */
        fun ReadActivities(ctx: Context) {
            itemsFile = OpenFile(ctx, itemsFileName)

            // Load file.
            val inputAsString =
                FileInputStream(itemsFile).bufferedReader().use { it.readText() }
            list.vs.clear() // Delete junk, only items from the file.
            list.vs.add(restItem)
            for (s in inputAsString.split("\n")) {
                Log.i("List", s)
                var v = s.split("\t")
                if (v.size < 1) continue // A comment or separator line.
                var a = v[0]
                var c = Color.LTGRAY
                if (v.size >= 2) c = v[1].toInt()

                if (a != "" && a != restLabel) {
                    list.vs.add(ActivityItem(a, c))
                }
            }
        }

        fun iter(): Iterator<ActivityItem> {
            return list.vs.listIterator()
        }

        fun Add(a: String, c: Int) {
            Log.i(logTag, "Adding ${a}/${c}")
            list.vs.add(ActivityItem(a, c))
            save()
        }

        fun Del(a: String) {
            Log.i(logTag, "Deleting ${a}")
            for (l in list.vs.listIterator()) {
                if (l.a == a) {
                    list.vs.remove(l)
                    break
                }
            }
            save()
        }

        private fun save() {
            FileOutputStream(itemsFile).use {
                for (l in list.vs.listIterator()) {
                    if (l.a != "") {
                        it.write("${l.a}\t${l.c}\n".toByteArray())
                    }
                }
            }
        }
    }

}

