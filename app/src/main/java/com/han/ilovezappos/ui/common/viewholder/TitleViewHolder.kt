package com.han.ilovezappos.ui.common.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.han.ilovezappos.R
import com.han.ilovezappos.model.Order

class TitleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val viewPrice: TextView = itemView.findViewById(R.id.view_title)

    fun bindDate(title: String) {
        viewPrice.text = title
    }

    companion object {
        fun newInstance(inflater: LayoutInflater, parent: ViewGroup) =
            TitleViewHolder(inflater.inflate(R.layout.item_title, parent, false))
    }
}