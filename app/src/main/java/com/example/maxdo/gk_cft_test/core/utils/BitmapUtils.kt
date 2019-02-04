package com.example.maxdo.gk_cft_test.core.utils

import android.graphics.Bitmap
import android.graphics.Matrix

fun rotateBitmap(source: Bitmap, angle: Float): Bitmap {
    val matrix = Matrix()
    matrix.postRotate(angle)
    return Bitmap.createBitmap(source, 0, 0, source.width, source.height, matrix, true)
}


private val RGB_MASK = 0x00FFFFFF

fun invert(original: Bitmap): Bitmap {
    // Create mutable Bitmap to invert, argument true makes it mutable
    val inversion = original.copy(Bitmap.Config.ARGB_8888, true)

    // Get info about Bitmap
    val width = inversion.width
    val height = inversion.height
    val pixels = width * height

    // Get original pixels
    val pixel = IntArray(pixels)
    inversion.getPixels(pixel, 0, width, 0, 0, width, height)

    // Modify pixels
    for (i in 0 until pixels)
        pixel[i] = pixel[i] xor RGB_MASK
    inversion.setPixels(pixel, 0, width, 0, 0, width, height)

    // Return inverted Bitmap
    return inversion
}
