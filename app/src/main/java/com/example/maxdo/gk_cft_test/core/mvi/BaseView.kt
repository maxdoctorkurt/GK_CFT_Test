package com.example.maxdo.gk_cft_test.core.mvi

import com.hannesdorfmann.mosby3.mvp.MvpView

interface BaseView<ViewStateClass>: MvpView {

    fun render(viewState: ViewStateClass)
}