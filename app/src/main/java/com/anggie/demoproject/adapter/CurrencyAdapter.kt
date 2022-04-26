package com.anggie.demoproject.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anggie.demoproject.R
import com.anggie.demoproject.database.Currency

class CurrencyAdapter(var currencyList: ArrayList<Currency>) : RecyclerView.Adapter<CurrencyAdapter.ViewHolder>(), Filterable{
    var currencyListFilter: ArrayList<Currency> = ArrayList()

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        override fun onClick(p0: View?) {
            TODO("Not yet implemented")
        }

        private val currencyIcon = itemView.findViewById<TextView>(R.id.ic_currency)
        private val currencyName = itemView.findViewById<TextView>(R.id.tv_currency_name)
        private val currencySymbol = itemView.findViewById<TextView>(R.id.tv_currency_symbol)

        fun bind(currency: Currency) {
            currencyIcon?.text = currency.name.subSequence(0,1)
            currencyName?.text = currency.name
            currencySymbol?.text = currency.symbol
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.currency_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currencyList[position])
    }

    override fun getItemCount(): Int {
        return currencyList.size
    }

    override fun getFilter(): Filter {
        return doFilter

    }

    private var doFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList: ArrayList<Currency> = ArrayList()
            if (constraint == null || constraint.isEmpty()) {
                filteredList.addAll(currencyListFilter)
            } else {
                for (currency in currencyListFilter) {
                    if (isContain(currency, constraint))
                        filteredList.add(currency)
                }
            }
            val filterResult = FilterResults()
            filterResult.values = filteredList
            return filterResult
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun publishResults(constraint: CharSequence?, filterResults: FilterResults?) {
            currencyList.clear()
            currencyList.addAll((filterResults?.values as Collection<Currency>))
            notifyDataSetChanged()
        }

        fun isContain(currency: Currency, constraint: CharSequence?): Boolean {
            if (currency.id.lowercase().contains(constraint.toString().lowercase()) ||
                currency.name.lowercase().contains(constraint.toString().lowercase()))
                    return true

            return false
        }
    }

    init {
        currencyListFilter.addAll(currencyList)
    }
}
