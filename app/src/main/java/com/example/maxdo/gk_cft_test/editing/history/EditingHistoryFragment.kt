package com.example.maxdo.gk_cft_test.editing.history


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.maxdo.gk_cft_test.R
import com.hannesdorfmann.mosby3.mvi.MviFragment

class EditingHistoryFragment : MviFragment<EditingHistoryView, EditingHistoryPresenter>(),
    EditingHistoryView {


    override fun render(viewState: EditingHistoryViewState) {
    }


    override fun createPresenter(): EditingHistoryPresenter {
        return EditingHistoryPresenter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.images_history_panel, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

}
