package com.asemlab.samples.base.utils

import android.graphics.Color


interface Dark {
    fun getDarkTextColor(): Int
    fun getLightTextColor(): Int
}

enum class Colors(val hex: String) : Dark {
    RED("#F44336") {
        override fun getDarkTextColor(): Int {
            return Color.parseColor(WHITE.hex)
        }

        override fun getLightTextColor(): Int {
            return Color.parseColor(WHITE.hex)
        }

    },

    YELLOW("#FFC107") {
        override fun getDarkTextColor(): Int {
            return Color.parseColor(BLACK.hex)
        }

        override fun getLightTextColor(): Int {
            return Color.parseColor(BLACK.hex)
        }
    },
    BLUE("#2196F3") {
        override fun getDarkTextColor(): Int {
            return Color.parseColor(BLACK.hex)
        }

        override fun getLightTextColor(): Int {
            return Color.parseColor(WHITE.hex)
        }
    },

    WHITE("#FFFFFF") {
        override fun getDarkTextColor(): Int {
            return Color.parseColor(hex)
        }

        override fun getLightTextColor(): Int {
            return Color.parseColor(BLACK.hex)
        }
    },
    BLACK("#000000") {
        override fun getDarkTextColor(): Int {
            return Color.parseColor(hex)
        }

        override fun getLightTextColor(): Int {
            return Color.parseColor(WHITE.hex)
        }
    };

}