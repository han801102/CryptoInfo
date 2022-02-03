package com.han.cryptoinfo.ui.common


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.han.cryptoinfo.db.entity.PriceAlert
import com.han.cryptoinfo.model.Order
import com.han.cryptoinfo.ui.common.viewholder.OrderViewHolder
import com.han.cryptoinfo.ui.common.viewholder.PriceAlertViewHolder
import com.han.cryptoinfo.ui.common.viewholder.TitleViewHolder

/**
 * [RecyclerView.Adapter]
 * currently display [OrderViewHolder] and [TitleViewHolder]
 * indicate different view type to dynamic add new view holder
 * specified [OnAdapterItemEventListener].to indicate different item event
 */
class DynamicAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var data = ArrayList<Any>()
        set(value) {
            data.clear()
            data.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewType.TITLE ->
                TitleViewHolder.newInstance(LayoutInflater.from(parent.context), parent)
            ViewType.ORDER ->
                OrderViewHolder.newInstance(LayoutInflater.from(parent.context), parent)
            ViewType.PRICE_ALERT ->
                PriceAlertViewHolder.newInstance(LayoutInflater.from(parent.context), parent)
            else -> throw Exception("Not support view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // TODO: Create abstract view holder or interface
        when (holder) {
            is TitleViewHolder -> {
                (data[position] as? String)?.let {
                    holder.bindDate(it)
                }
            }
            is OrderViewHolder -> {
                (data[position] as? Order)?.let {
                    holder.bindDate(it)
                }
            }
            is PriceAlertViewHolder -> {
                (data[position] as? PriceAlert)?.let {
                    holder.bindDate(it)
                }
            }
            else -> throw Exception("Not support view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (data[position]) {
            is String -> ViewType.TITLE
            is Order -> ViewType.ORDER
            is PriceAlert -> ViewType.PRICE_ALERT
            else -> super.getItemViewType(position)
        }
    }

    override fun getItemCount(): Int = data.size

    object ViewType {
        const val TITLE = 0
        const val ORDER = 1
        const val PRICE_ALERT = 2
    }

    // TODO: Implement adapter item event
    interface OnAdapterItemEventListener {
        fun onItemClick() {}
    }
}


