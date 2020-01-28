package com.han.ilovezappos.ui.pricealert

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer

import com.han.ilovezappos.R
import com.han.ilovezappos.db.entity.PriceAlert
import com.han.ilovezappos.ui.common.DynamicAdapter
import kotlinx.android.synthetic.main.fragment_order_book.recycler_view
import kotlinx.android.synthetic.main.fragment_price_alert.*

class PriceAlertFragment : Fragment() {

    companion object {
        fun newInstance() = PriceAlertFragment()
    }

    private lateinit var viewModel: PriceAlertViewModel
    private val adapter = DynamicAdapter()
    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            s?.let {
                if (isInputValid(s)) {
                    price_input_layout.isErrorEnabled = false
                }
            }
        }

        override fun afterTextChanged(s: Editable?) {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_price_alert, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initInputEditText()

        viewModel = ViewModelProviders.of(this).get(PriceAlertViewModel::class.java)
        viewModel.allPriceAlert.observe(viewLifecycleOwner, Observer {

            adapter.data = it as ArrayList<Any>
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        price_input.removeTextChangedListener(textWatcher)
    }

    private fun initRecyclerView() {
        recycler_view.adapter = adapter
    }

    private fun initInputEditText() {
        price_input_layout.error = "You need to enter a number"
        price_input_layout.isErrorEnabled = false
        price_input.setOnEditorActionListener { v, actionId, event ->
            when {
                !isInputValid(v.text) -> price_input_layout.isErrorEnabled = true
                actionId == EditorInfo.IME_ACTION_DONE -> {
                    viewModel.insert(PriceAlert(0, v.text.toString().toDouble()))
                    v.clearFocus()
                }
            }
            false
        }
        price_input.addTextChangedListener(textWatcher)
    }

    private fun isInputValid(text: CharSequence): Boolean {
        return text.isNotBlank()
    }


}
