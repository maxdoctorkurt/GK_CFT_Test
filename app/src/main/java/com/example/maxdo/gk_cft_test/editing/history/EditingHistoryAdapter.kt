package com.example.maxdo.gk_cft_test.editing.history

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.maxdo.gk_cft_test.core.entities.EditingHistoryElement
import com.example.maxdo.gk_cft_test.core.share.ChangeableDataSetListAdapter
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.ProgressBar
import com.example.maxdo.gk_cft_test.R
import com.squareup.picasso.Picasso
import java.io.File

class EditingHistoryAdapter : ChangeableDataSetListAdapter<EditingHistoryElement, HistoryViewHolder>() {

    val historyData = mutableListOf<EditingHistoryElement>()

    override fun addData(toAdd: List<EditingHistoryElement>) {
        historyData.addAll(toAdd)
        notifyDataSetChanged()
    }

    override fun setData(toSet: List<EditingHistoryElement>) {
        historyData.clear()
        historyData.addAll(toSet)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.editing_history_row, parent, false)
        return HistoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return historyData.size
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {

        val isShadeRow = position % 2 == 0
        val dataElem = historyData[position]

        if (dataElem.imageProcessingProgressPercentage == 100 && dataElem.fullPathToImageFile != null)
            Picasso.get().load(File(dataElem.fullPathToImageFile)).error(R.drawable.broken_image).into(holder.imagePreview)

        holder.progressBar.progress = dataElem.imageProcessingProgressPercentage

        holder.rowShader.visibility = if (isShadeRow) View.VISIBLE else View.INVISIBLE
    }

}

class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var progressBar = view.findViewById<ProgressBar>(R.id.imageProcessingProgress)
    var imagePreview = view.findViewById<ImageView>(R.id.imagePreview)
    var rowShader = view.findViewById<View>(R.id.rowShader)
}