package com.han.cryptoinfo.ui.orderbook

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.han.cryptoinfo.R
import com.han.cryptoinfo.ui.common.DynamicAdapter
import kotlinx.android.synthetic.main.fragment_order_book.*

class OrderBookFragment : Fragment() {

    companion object {
        fun newInstance() = OrderBookFragment()
    }

    private lateinit var viewModel: OrderBookViewModel
    private val adapter = DynamicAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_order_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initChipGroup()

        viewModel = ViewModelProviders.of(this).get(OrderBookViewModel::class.java)
        viewModel.orderBook.observe(viewLifecycleOwner, Observer {
            view_loading.visibility = View.GONE
            adapter.data = it.asks
        })
    }

    private fun initRecyclerView() {
        recycler_view.adapter = adapter
    }

    private fun initChipGroup() {
        chip_group.setOnCheckedChangeListener { chipGroup, id ->
            viewModel.orderBook.value?.let {
                when (id) {
                    R.id.chip_asks -> adapter.data = it.asks
                    R.id.chip_bids -> adapter.data = it.bids
                }
            }

        }
    }


}
