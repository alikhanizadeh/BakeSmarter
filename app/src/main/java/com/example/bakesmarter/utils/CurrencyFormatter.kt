package com.example.bakesmarter.utils

import java.text.DecimalFormat

fun formatRial(value: Double): String {
    val formatter = DecimalFormat("#,###")
    return "${formatter.format(value)} Rial"
}