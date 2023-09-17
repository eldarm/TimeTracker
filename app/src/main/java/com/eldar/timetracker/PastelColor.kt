package com.eldar.timetracker

import android.graphics.Color

public val PastelColors = arrayOf(
    PastelColor.SYSTEM_REST,
    PastelColor.KISS,
    PastelColor.GRASS,
    PastelColor.SKY,
    PastelColor.YOLK,
)

class PastelColor {
    companion object {
        val SYSTEM_REST = Color.rgb(0x80,0xFF,0x80)
        val KISS = Color.rgb(0xFF, 0xC0, 0xC0)
        val GRASS = Color.rgb(0xC0, 0xFF, 0xC0)
        val SKY = Color.rgb(0xC0, 0xE0, 0xFF)
        val YOLK = Color.rgb(0xFF, 0xFF, 0xC0)
    }
}
