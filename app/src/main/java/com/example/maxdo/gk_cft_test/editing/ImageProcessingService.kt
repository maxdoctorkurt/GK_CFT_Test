package com.example.maxdo.gk_cft_test.editing

import android.app.Service
import android.content.Intent
import android.os.IBinder
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class ImageProcessingService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        println("*** onCreate")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        val extras = intent.extras

        if (extras != null) {
            val url = extras.getString(IMAGE_URL_KEY)
            val operation = extras.getInt(IMAGE_OPERATION_KEY)

            if (url != null) data.onNext(ImageProcessingRequest(url, operation))
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        println("*** onDestroy")
    }

    private fun getProcessingObservable(): Observable<ImageProcessingResult> {
        return data.map {
            ImageProcessingResult("stub", 0f)
        }
    }

    companion object {
        fun getProcessingResultsObservable(): Observable<ImageProcessingResult> = getProcessingResultsObservable()
        private val data = PublishSubject.create<ImageProcessingRequest>()
        val IMAGE_URL_KEY = "image_url"
        val IMAGE_OPERATION_KEY = "image_op"
    }
}

class ImageProcessingRequest(val imageUrl: String, val operationTypeOrdinal: Int)

enum class EImageOperation {
    MIRROR,
    GRAY_SCALE,
    ROTATE_90_CW,
    INVERT_COLORS
}

class ImageProcessingResult(val newImageUrl: String?, val progressPercentage: Float)