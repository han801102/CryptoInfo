package com.han.cryptoinfo.ui.common.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.han.cryptoinfo.R
import com.han.cryptoinfo.db.entity.PriceAlert

import java.text.NumberFormat
import java.util.*


class PriceAlertViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    private val viewPrice: TextView = itemView.findViewById(R.id.view_price_alert)

    fun bindDate(priceAlert: PriceAlert) {
        val format = NumberFormat.getCurrencyInstance(Locale.getDefault())
        format.currency = Currency.getInstance("USD")
        viewPrice.text = format.format(priceAlert.price)
    }

    companion object {
        fun newInstance(inflater: LayoutInflater, parent: ViewGroup) =
            PriceAlertViewHolder(inflater.inflate(R.layout.item_price_alert, parent, false))
    }
}