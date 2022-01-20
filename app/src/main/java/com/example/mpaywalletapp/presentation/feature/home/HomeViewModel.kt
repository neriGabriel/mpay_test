package com.example.mpaywalletapp.presentation.feature.home

import androidx.lifecycle.*
import com.example.mpaywalletapp.data.api.WidgetService
import com.example.mpaywalletapp.data.repository.WidgetRepositoryImpl
import com.example.mpaywalletapp.data.utils.NetworkUtils
import com.example.mpaywalletapp.domain.entity.SimpleWidget
import com.example.mpaywalletapp.domain.repository.WidgetRepository
import com.example.mpaywalletapp.domain.utils.Resource
import com.example.mpaywalletapp.domain.utils.Status
import com.example.mpaywalletapp.presentation.feature.home.HomeConstants.HOME_HEADER_WIDGET
import kotlinx.coroutines.launch
import org.koin.experimental.property.inject
import java.lang.Exception

class HomeViewModel(private val widgetRepository: WidgetRepository) : ViewModel() {

    private val _homeUIState = MutableLiveData<Resource<List<SimpleWidget>>>()
    val homeUIState: LiveData<Resource<List<SimpleWidget>>>
        get() = _homeUIState

    private val _welcomeHomeState = MutableLiveData<Resource<SimpleWidget>>()
    val welcomeHomeState: LiveData<Resource<SimpleWidget>>
        get() = _welcomeHomeState


    private fun fetchWidgets() {
        _homeUIState.postValue(Resource.loading())
        viewModelScope.launch {
            try{
                val result = widgetRepository.getWidgets()
                val tempList = mutableListOf<SimpleWidget>()
                when(result.status) {
                    Status.SUCCESS -> {
                        result.data?.forEach {
                            if (it.identifier.equals(HOME_HEADER_WIDGET))
                                _welcomeHomeState.postValue(Resource.success(it))
                            else
                                tempList.add(it)
                        }
                        _homeUIState.postValue(Resource.success(tempList))
                    }
                }
            } catch(e: Exception) {
                _homeUIState.postValue(Resource.error(e.message))
            }
        }
    }

    init {
        fetchWidgets()
    }

}