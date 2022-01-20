package com.example.mpaywalletapp.data.repository

import com.example.mpaywalletapp.data.api.WidgetService
import com.example.mpaywalletapp.domain.entity.SimpleWidget
import com.example.mpaywalletapp.domain.entity.Widgets
import com.example.mpaywalletapp.domain.repository.WidgetRepository
import com.example.mpaywalletapp.domain.utils.Resource

class WidgetRepositoryImpl(private val service: WidgetService) : WidgetRepository {
    override suspend fun getWidgets(): Resource<List<SimpleWidget>> {
        val response = service.getWidgets()
        return if(response.isSuccessful) {
            Resource.success(convertToUIState(response.body()))
        } else {
            Resource.error("Error: ${response.message()}")
        }
    }

    fun convertToUIState(widgets: Widgets?): List<SimpleWidget> {
        val tempList = arrayListOf<SimpleWidget>()
        widgets?.widgetList?.forEach {
            val simpleWidget = SimpleWidget(
                    identifier = it.identifier,
                    contentTitle = it.content.title,
                    contentType = it.content.cardNumber,
                    label = it.content.balance?.label,
                    value = it.content.balance?.value,
                    buttonTitle = it.content.button?.text,
                    buttonAction = it.content.button?.action?.identifier,
                    buttonContent = it.content.button?.action?.content?.cardId
                            ?: it.content.button?.action?.content?.accountId
            )
            tempList.add(simpleWidget)
        }
        return tempList
    }
}