package com.eldar.timetracker

import android.graphics.Color

public val PastelColors = arrayOf(
    PastelColor.KISS,
    PastelColor.GRASS,
    PastelColor.SKY,
    PastelColor.YOLK,
)

class PastelColor {
    companion object {
        val KISS = Color.rgb(255, 192, 192)
        val GRASS = Color.rgb(192, 255, 192)
        val SKY = Color.rgb(128, 192, 255)
        val YOLK = Color.rgb(255, 255, 192)
    }
}
