package com.example.maxdo.gk_cft_test.core.mvi

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpView

abstract class BasePresenter<ViewClass : MvpView, ViewStateClass, PartialStateClass> : MviBasePresenter<ViewClass, ViewStateClass>(){
    abstract fun stateReducer(viewState: ViewStateClass, partialState: PartialStateClass): ViewStateClass
}