package com.example.mpaywalletapp.presentation.feature.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mpaywalletapp.R
import com.example.mpaywalletapp.domain.entity.SimpleWidget
import com.example.mpaywalletapp.domain.repository.WidgetRepository
import com.example.mpaywalletapp.domain.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.java.KoinJavaComponent.inject

class HomeFragment : Fragment() {

    private val homeViewModel : HomeViewModel by viewModel()
    private lateinit var recyclerView: RecyclerView
    private lateinit var widgetAdapter: WidgetAdapter

    private lateinit var homeLoading: LinearLayout
    private lateinit var homeContainer: LinearLayout

    private lateinit var welcomeTitle : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    @SuppressLint("ShowToast")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.list_widget_view)
        recyclerView.layoutManager = LinearLayoutManager(context)

        welcomeTitle = view.findViewById(R.id.welcome_title)
        homeLoading = view.findViewById(R.id.home_loading)
        homeContainer = view.findViewById(R.id.home_container)

        widgetAdapter = WidgetAdapter(arrayListOf())
        recyclerView.adapter = widgetAdapter
        setupObservers()

    }

    private fun setupObservers() {
        homeViewModel.homeUIState.observe(viewLifecycleOwner, { homeResourceWidget ->
            when(homeResourceWidget.status) {
                Status.SUCCESS -> {
                    renderWidgetsList(homeResourceWidget.data)
                    homeContainer.visibility = View.VISIBLE
                    homeLoading.visibility = View.GONE
                }

                Status.LOADING -> {
                    homeContainer.visibility = View.GONE
                    homeLoading.visibility = View.VISIBLE
                }

                Status.ERROR -> {
                    Toast.makeText(context, "Failed to load the widgets", Toast.LENGTH_SHORT)
                            .show()
                }
            }
        })

        homeViewModel.welcomeHomeState.observe(viewLifecycleOwner, { homeWelcomeResourceWidget ->
            when(homeWelcomeResourceWidget.status) {
                Status.SUCCESS -> {
                    renderWelcomeInfo(homeWelcomeResourceWidget.data?.contentTitle)
                }
                Status.ERROR -> {
                    Toast.makeText(context, "Failed to load welcome message",
                            Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

    private fun renderWidgetsList(widgetList: List<SimpleWidget>?) {
        widgetList?.let { widgetAdapter.addData(it) }
    }

    private fun renderWelcomeInfo(title: String?) {
        welcomeTitle.text = title
    }


}