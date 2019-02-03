package com.example.maxdo.gk_cft_test.editing.modifiersPanel

import com.example.maxdo.gk_cft_test.core.mvi.BasePresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.File
import java.util.concurrent.TimeUnit

class EditingImagePresenter : BasePresenter<EditingImageView, EditingImageViewState, EditingImagePartialViewState>() {

    override fun attachView(view: EditingImageView) {
        println("*** attachView")

        super.attachView(view)
    }

    override fun detachView() {
        println("*** detachView")
        super.detachView()
    }

    override fun bindIntents() {

        println("*** bindIntents")


        val initialState = EditingImageViewState()

        val getImagePartialState:Observable<EditingImagePartialViewState> = intent(EditingImageView::getImageIntent)
            .doOnNext { println("*** !!!!!!") }
            .observeOn(Schedulers.io())
            .debounce(300, TimeUnit.MILLISECONDS)
            .map {
                EditingImagePartialViewState.Preview(it, false)
            }

        val allObservables = getImagePartialState.scan(initialState, this::stateReducer).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())

        subscribeViewState(allObservables, EditingImageView::render)
    }

    override fun stateReducer(viewState: EditingImageViewState, partialState: EditingImagePartialViewState): EditingImageViewState {

        if(partialState is EditingImagePartialViewState.Preview) {
            viewState.isProgress = partialState.isProgress
            viewState.previewImage = partialState.previewImage
            return viewState
        }

        throw IllegalStateException("Unknown partial!")

    }

}


sealed class EditingImagePartialViewState {
    class Preview(val previewImage: File? = null, val isProgress: Boolean = true) : EditingImagePartialViewState()
//    class Preview(val PreviewUrl: String) : EditingImagePartialViewState()
}