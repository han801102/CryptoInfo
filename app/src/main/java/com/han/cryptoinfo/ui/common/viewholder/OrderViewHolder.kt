package com.han.cryptoinfo.ui.common.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.han.cryptoinfo.R
import com.han.cryptoinfo.model.Order

class OrderViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    private val viewPrice: TextView = itemView.findViewById(R.id.view_price)

    fun bindDate(order: Order) {
        viewPrice.text = view.context?.getString(R.string.order_price, order.amount, order.price)
    }

    companion object {
        fun newInstance(inflater: LayoutInflater, parent: ViewGroup) =
            OrderViewHolder(inflater.inflate(R.layout.item_order, parent, false))
    }
}