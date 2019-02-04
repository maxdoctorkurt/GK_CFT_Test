package com.example.maxdo.gk_cft_test.editing.history


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.maxdo.gk_cft_test.R
import com.example.maxdo.gk_cft_test.core.entities.EditingHistoryElement
import com.hannesdorfmann.mosby3.mvi.MviFragment
import kotlinx.android.synthetic.main.images_history_panel.*

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = EditingHistoryAdapter()
        rv_imageProcessingHistory.adapter = adapter
        rv_imageProcessingHistory.layoutManager = LinearLayoutManager(context)


        // data stub
        adapter.setData(listOf(EditingHistoryElement(), EditingHistoryElement(), EditingHistoryElement(), EditingHistoryElement(), EditingHistoryElement()))

        fl_listProgress.visibility = View.GONE


    }

}
