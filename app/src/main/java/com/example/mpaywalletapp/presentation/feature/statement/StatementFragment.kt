package com.example.mpaywalletapp.presentation.feature.statement

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mpaywalletapp.R
import com.example.mpaywalletapp.domain.entity.Transaction
import com.example.mpaywalletapp.domain.utils.Status
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.experimental.property.inject

class StatementFragment : Fragment() {

    private val args: StatementFragmentArgs by navArgs()
    private var id: String? = null
    private val statementViewModel: StatementViewModel by viewModel()

    private lateinit var recyclerView: RecyclerView
    private lateinit var statementAdapter: StatementAdapter

    private lateinit var availableLimit: TextView
    private lateinit var availableLabel: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_statement, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        id = args.statementId

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        statementAdapter = StatementAdapter(arrayListOf())
        recyclerView.adapter = statementAdapter

        availableLimit = view.findViewById(R.id.available_limit)
        availableLabel = view.findViewById(R.id.available_label)

        statementViewModel.fetchStatement(id)

        setupObservers()
    }

    private fun setupObservers() {
        statementViewModel.statementSate.observe(viewLifecycleOwner, {
            when(it.status) {
                Status.SUCCESS -> {
                    renderTransactionList(it.data?.transactions)
                    renderStatementInfo(it.data?.balance?.label, it.data?.balance?.value)
                }
            }
        })
    }

    private fun renderTransactionList(data: List<Transaction>?) {
        data?.let { statementAdapter.addData(it) }
    }

    private fun renderStatementInfo(label: String?, value: String?) {
        availableLimit.text = value
        availableLabel.text = label
    }
}