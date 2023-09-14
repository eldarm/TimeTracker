package com.eldar.timetracker

import android.content.Context
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import android.util.Log
import java.text.SimpleDateFormat
import java.util.Calendar

// TODO: refactor. Separate file operations.
private var itemsFile: File? = null
private val itemsFileName ="items.tsv"

class ActivitiesList {
    private var vs = mutableListOf(restLabel)

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
            list.vs.add(restLabel)
            for (s in inputAsString.split("\n")) {
                if (s != "" && s != restLabel) {
                    list.vs.add(s)
                }
            }
        }

        fun iter(): Iterator<String> {
            return list.vs.listIterator()
        }

        fun Add(a: String) {
            list.vs.add(a)
            save()
        }

        fun Del(a: String) {
            list.vs.remove(a)
            save()
        }

        private fun save() {
            FileOutputStream(itemsFile).use {
                for (l in list.vs.listIterator()) {
                    if (l != "") {
                        it.write((l + "\n").toByteArray())
                    }
                }
            }
        }
    }

}

