package com.example.mpaywalletapp.presentation.feature.statement

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mpaywalletapp.R
import com.example.mpaywalletapp.domain.entity.Transaction

class StatementAdapter(private var transactionList: List<Transaction>)
    : RecyclerView.Adapter<StatementAdapter.StatementAdapterViewHolder>() {

    class StatementAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val transactionTitle: TextView = view.findViewById(R.id.transaction_title)
        val transactionSubtitle: TextView = view.findViewById(R.id.transaction_subtitle)
        val transactionAmount: TextView = view.findViewById(R.id.transaction_amount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatementAdapterViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.statement_item, parent, false)
        return StatementAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: StatementAdapterViewHolder, position: Int) {
        holder.transactionTitle.text = transactionList[position].label
        holder.transactionSubtitle.text =  transactionList[position].description
        holder.transactionAmount.text = transactionList[position].value
    }

    override fun getItemCount() = transactionList.size

    fun addData(dataList: List<Transaction>) {
        transactionList = dataList
        notifyDataSetChanged()
    }
}