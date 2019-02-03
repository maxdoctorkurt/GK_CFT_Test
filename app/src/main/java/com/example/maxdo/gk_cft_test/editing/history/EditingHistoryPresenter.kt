package com.example.maxdo.gk_cft_test.editing.history

import com.example.maxdo.gk_cft_test.core.mvi.BasePresenter
import io.reactivex.Observable

class EditingHistoryPresenter :
    BasePresenter<EditingHistoryView, EditingHistoryViewState, EditingHistoryPartialState>() {


    override fun stateReducer(
        viewState: EditingHistoryViewState,
        partialState: EditingHistoryPartialState
    ): EditingHistoryViewState {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun bindIntents() {
        subscribeViewState(Observable.empty(), EditingHistoryView::render)


    }

}


class EditingHistoryPartialState {

}
