package com.example.mpaywalletapp.presentation.feature.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.mpaywalletapp.R
import com.example.mpaywalletapp.domain.entity.SimpleWidget

class WidgetAdapter(private var widgetList: List<SimpleWidget>) :
        RecyclerView.Adapter<WidgetAdapter.WidgetAdapterViewHolder>() {

    class WidgetAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val widgetAction : Button = view.findViewById(R.id.widget_action)
        val widgetTitle : TextView = view.findViewById(R.id.widget_title)
        val widgetSubtitle : TextView = view.findViewById(R.id.widget_subtitle)
        val widgetAmout : TextView = view.findViewById(R.id.widget_amount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WidgetAdapterViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.widget_item, parent, false)
        return WidgetAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: WidgetAdapterViewHolder, position: Int) {
        holder.widgetTitle.text = widgetList[position].contentTitle
        holder.widgetAction.text = widgetList[position].buttonTitle

        holder.widgetAction.setOnClickListener {
            when(widgetList[position].identifier) {
                HomeConstants.STATEMENT_SCREEN -> {
                    Navigation.findNavController(holder.itemView)
                            .navigate(HomeFragmentDirections.actionHomeFragmentToStatementFragment(
                                    widgetList[position].buttonContent
                            ))
                }

                HomeConstants.CARD_SCREEN -> {
                    Navigation.findNavController(holder.itemView)
                            .navigate(HomeFragmentDirections.actionHomeFragmentToCardFragment(
                                    widgetList[position].buttonContent
                            ))
                }
            }
        }

        if(widgetList[position].identifier == HomeConstants.STATEMENT_SCREEN ) {
            holder.widgetSubtitle.text = widgetList[position].label
            holder.widgetAmout.visibility = View.VISIBLE
            holder.widgetAmout.text = widgetList[position].value
        } else {
            holder.widgetSubtitle.text = widgetList[position].contentType
        }
    }

    override fun getItemCount() = widgetList.size

    fun addData(dataList: List<SimpleWidget>) {
        widgetList = dataList
        notifyDataSetChanged()
    }

}