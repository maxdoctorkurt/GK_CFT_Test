package com.example.maxdo.gk_cft_test.editing

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.graphics.Matrix
import android.net.Uri
import androidx.exifinterface.media.ExifInterface
import com.example.maxdo.gk_cft_test.core.rotateBitmap
import java.io.File


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
            val imageFullPath = extras.getString(IMAGE_FULL_PATH_KEY)
            val operation = extras.getInt(IMAGE_OPERATION_KEY)

            if (imageFullPath != null)
                subject.onNext(ImageProcessingRequest(imageFullPath, operation, this))


        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        println("*** onDestroy")
    }



    companion object {

        private val subject = PublishSubject.create<ImageProcessingRequest>()

        val imageProcessingObservable: Observable<ImageProcessingResult> = subject
            .observeOn(Schedulers.computation())
            .map {

                val request = it
                val path = request.imageFullPath
                val operation = request.imageOperationOrdinal
                var bitmap = BitmapFactory.decodeFile(path)
                val context = request.context


                // TODO move
                val inputStream =  context.contentResolver.openInputStream(Uri.fromFile(File(path)))

                if(inputStream != null) {
                    val exifInterface = ExifInterface(inputStream)
//                    exifInterface.
                }

                when(operation) {
                    EImageOperation.ROTATE_90_CW.ordinal -> {


                        bitmap = rotateBitmap(bitmap, -90f)






                    }
                    EImageOperation.GRAY_SCALE.ordinal -> {}
                    EImageOperation.INVERT_COLORS.ordinal -> {}
                    EImageOperation.MIRROR.ordinal -> {}
                }




                ImageProcessingResult(null, -1f)
            }

        const val IMAGE_FULL_PATH_KEY = "image_full_path"
        const val IMAGE_OPERATION_KEY = "image_operation"
    }
}

class ImageProcessingRequest(val imageFullPath: String, val imageOperationOrdinal: Int, val context: Context)

enum class EImageOperation {
    MIRROR,
    GRAY_SCALE,
    ROTATE_90_CW,
    INVERT_COLORS
}

class ImageProcessingResult(val newImageFullPath: String?, val progressPercentage: Float)