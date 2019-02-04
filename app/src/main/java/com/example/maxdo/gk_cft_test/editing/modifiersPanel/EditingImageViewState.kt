package com.example.maxdo.gk_cft_test.editing.modifiersPanel

import com.example.maxdo.gk_cft_test.editing.ImageProcessingResult
import java.io.File

class EditingImageViewState(
    var previewImage: File? = null,
    var isProgress: Boolean = false,
    var imageProcessingResult: ImageProcessingResult? = null
)
