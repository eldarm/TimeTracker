package com.eldar.timetracker

import android.content.Context
import android.os.Environment
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import android.util.Log
import android.content.Context.MODE_PRIVATE

import java.io.OutputStream


class ActivitiesList {
    private var vs = mutableListOf(restLabel)

    companion object {
        private val restLabel = "REST (stop tracking)"
        @JvmStatic
        private var list: ActivitiesList = ActivitiesList()

        var writePath: File? = null
        var itemsFile: File? = null

        public fun SetContext(ctx: Context) {

            val dataPath = ctx.filesDir // Environment.getDataDirectory()
            Log.i("ActivitiesList", "Data path $dataPath exists? " + dataPath?.exists())
            Log.i("ActivitiesList", "Data path $dataPath readable? " + dataPath?.canRead())
            Log.i("ActivitiesList", "Data path $dataPath writeable? " + dataPath?.canWrite())

            writePath = File(dataPath!!.path, "ElsTimeTracker")
            Log.i("ActivitiesList", "Is there folder $writePath")
            if (!writePath!!.exists()) {
                Log.i("ActivitiesList", "Creating folder $writePath")
                if (!writePath!!.mkdir()) {
                    Log.i("ActivitiesList", "Failed to create folder $writePath")
                }
            }

            itemsFile = File(writePath, "items.tsv")
            if (itemsFile!!.exists()) {
                Log.i("ActivitiesList", "File $writePath.path exists.")
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
            } else {
                Log.i("ActivitiesList", "No file $writePath.path")
                if (!itemsFile!!.createNewFile()) {
                    Log.i("ActivitiesList", "Cannot create file.")
                }
            }
        }

        public fun iter(): Iterator<String> {
            return list.vs.listIterator()
        }

        public fun Add(a: String) {
            list.vs.add(a)
            save()
        }

        public fun Del(a: String) {
            list.vs.remove(a)
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

