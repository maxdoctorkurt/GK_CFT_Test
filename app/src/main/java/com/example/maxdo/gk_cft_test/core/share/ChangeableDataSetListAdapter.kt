package com.example.maxdo.gk_cft_test.core.share

import androidx.recyclerview.widget.RecyclerView
import com.example.maxdo.gk_cft_test.editing.history.HistoryViewHolder

abstract class ChangeableDataSetListAdapter<ListElementType, ViewHolderClass : RecyclerView.ViewHolder>: RecyclerView.Adapter<ViewHolderClass>() {
    abstract fun addData(toAdd: List<ListElementType>)
    abstract fun setData(toSet: List<ListElementType>)
}