package com.example.maxdo.gk_cft_test.core.utils

import android.os.Environment
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


private fun generateImageFileNamePrefix(): String {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
    return "edited_image_" + timeStamp + "_"
}

fun createImageFile(): File {
    val storageDir = Environment.getExternalStoragePublicDirectory(
        Environment.DIRECTORY_PICTURES
    )

    return File.createTempFile(
        generateImageFileNamePrefix(),
        ".jpg",
        storageDir
    )
}

fun getAbsolutePath(file: File): String {
    return "file:" + file.absolutePath
}
