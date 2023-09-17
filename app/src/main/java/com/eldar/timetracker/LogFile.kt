package com.eldar.timetracker

import android.content.Context
import android.util.Log
import java.io.File
import java.io.FileFilter
import java.io.FileInputStream
import java.text.SimpleDateFormat
import java.util.Calendar

data class LogRecord(val ts: String, val t: Long, val a: String);

private var dataPath: File? = null
private var logFile: File? = null
private val logTag = "TimeTracker"
private val logTimeFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
private val logFilePattern = "log\\d{8}.log"
private val entries = mutableListOf<LogRecord>()

fun logFileName(): String {
    return "log" + SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().time) + ".log"
}

fun OpenFile(ctx: Context, name: String): File {
    Log.i(logTag, "Time now is " + Calendar.getInstance().time.time)
    Log.i(logTag, "Time now is " + Calendar.getInstance().time)
    Log.i(logTag, "Time now is " + logTimeFormat.format(Calendar.getInstance().time))


    dataPath = ctx.filesDir // Environment.getDataDirectory()
    Log.i(logTag, "Data path $dataPath exists? " + dataPath?.exists())
    Log.i(logTag, "Data path $dataPath readable? " + dataPath?.canRead())
    Log.i(logTag, "Data path $dataPath writeable? " + dataPath?.canWrite())

    val rFile = File(dataPath, name)
    if (rFile!!.exists()) {
        Log.i(logTag, "File ${rFile} exists.")
    } else {
        Log.i(logTag, "No file ${rFile}")
        if (!rFile!!.createNewFile()) {
            Log.e(logTag, "Cannot create ${rFile} file.")
        }
    }
    return rFile
}

fun StartActivity(a: String) {
    val t = Calendar.getInstance().time
    val lr = LogRecord(logTimeFormat.format(t), t.time, a)
    entries.add(lr)
    logFile?.appendText(lr.ts + "\t" + lr.t.toString() + "\t" + a + "\n")
    Log.i(logTag, lr.toString())
}

fun InitLog(ctx: Context) {
    logFile = OpenFile(ctx, logFileName())
    val inputAsString =
        FileInputStream(logFile).bufferedReader().use { it.readText() }
    for (s in inputAsString.split("\n")) {
        if (s == "") continue
        val f = s.split("\t")
        if (f.size < 3) {
            Log.e(logTag, "Poorly formatted log line $s")
        } else {
            var a = if (f[2].length > 20) f[2].subSequence(0, 20).toString() else f[2]
            entries.add(LogRecord(f[0], f[1].toLong(), a))
            Log.i(logTag, f.toString() + "\n")
        }
    }
}

fun Stats(days: Int): Map<String, Map<String, Long>> {
    var m: MutableMap<String, MutableMap<String, Long>> = mutableMapOf()
    var a = ""
    var t: Long = 0
    var d = ""
    entries.forEach {
        val k = it.ts.subSequence(0, 10).toString()
        Log.i(logTag, "'" + k + "'")
        if (k == d) {
            // Skip overnight entries.
            if (!m.containsKey(k)) m[k] = mutableMapOf<String, Long>()
            if (!m[k]!!.containsKey(it.a)) m[k]!![it.a] = 0
            m[k]!![it.a] = it.t - t + (m[k]!![it.a] ?: 0)
        }
        a = it.a
        t = it.t
        d = k
    }
    return m
}

fun Ms2String(ms: Long) : String {
    val s = ms / 1000;
    val m = s % 60
    val h = m / 3600
    return "%02dH:%02dM".format(h, m)
}
fun StatsStr() : String {
    var res = ""
    for (stat in Stats(1)) {
        for (it in stat.value) {
                if (it.key == ActivitiesList.restLabel) continue
                res += "%10s %-15s %-8s\n".format(stat.key, it.key, Ms2String(it.value))
        }
    }
    return res
}

fun ListFiles() : List<File>? {
    return dataPath?.listFiles()?.toList()?.filter { it -> it.toString().endsWith(".log") }
}
