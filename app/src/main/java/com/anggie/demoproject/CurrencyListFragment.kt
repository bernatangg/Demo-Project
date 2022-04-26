package com.anggie.demoproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anggie.demoproject.adapter.CurrencyAdapter
import com.anggie.demoproject.database.Currency
import kotlinx.android.synthetic.main.fragment_currency_list.*


class CurrencyListFragment : Fragment() {
    private val TAG = "TAG" + CurrencyListFragment::class.java
    var adapter: CurrencyAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_currency_list, container, false)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currencyViewModel: CurrencyViewModel by requireActivity().viewModels {
            CurrencyViewModelFactory((requireActivity().application as CurrencyApp).currencyRepo)
        }

        search_text!!.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("Not yet implemented")
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("Not yet implemented")
            }

            override fun afterTextChanged(text: Editable?) {
                if (text!!.isEmpty())
                    adapter?.filter?.filter(text)
            }
        })

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        read_more_icon!!.setOnClickListener {
            currencyViewModel.allCurrency.observe(requireActivity(), { currency ->
                currency?.let {
                    Log.d(TAG, "length " + it.size )
                    hint_text.visibility = View.INVISIBLE
                    val currencyList = ArrayList<Currency> ()
                    currencyList.addAll(it)
                    adapter = CurrencyAdapter(currencyList)
                    recyclerView.adapter = adapter
                }
            })

            sort_icon!!.setOnClickListener {
                Log.d(TAG, "sortButton onClick")
                if (adapter != null)
                    adapter!!.currencyList.sortBy { it.name }

                adapter!!.notifyDataSetChanged()
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CurrencyListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CurrencyListFragment().apply {
            }
    }
}