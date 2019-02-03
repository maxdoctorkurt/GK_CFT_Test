package com.example.maxdo.gk_cft_test.editing.modifiersPanel

import com.example.maxdo.gk_cft_test.core.mvi.BaseView
import io.reactivex.Observable
import java.io.File

interface  EditingImageView: BaseView<EditingImageViewState> {
    fun getImageIntent(): Observable<File>
    fun getRotateImageIntent(): Observable<Boolean>
    fun getInvertImageIntent(): Observable<Boolean>
    fun getMirrorImageIntent(): Observable<Boolean>
    fun getGreyScaleImageIntent(): Observable<Boolean>
}
