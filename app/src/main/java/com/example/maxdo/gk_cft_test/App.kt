package com.example.maxdo.gk_cft_test

import android.app.Application
import android.content.Intent
import com.example.maxdo.gk_cft_test.editing.EImageOperation
import com.example.maxdo.gk_cft_test.editing.ImageProcessingService

class App: Application() {

    init {
        instance = this
    }

    companion object {

        private lateinit var instance : App

        fun startImageProcessingService(imageFullPath: String, imageOperation: EImageOperation) {
            val serviceIntent = Intent(instance, ImageProcessingService::class.java)
            serviceIntent.putExtra(ImageProcessingService.IMAGE_OPERATION_KEY, imageOperation.ordinal);
            serviceIntent.putExtra(ImageProcessingService.IMAGE_FULL_PATH_KEY, imageFullPath);
            instance.startService(serviceIntent)
        }

    }

}