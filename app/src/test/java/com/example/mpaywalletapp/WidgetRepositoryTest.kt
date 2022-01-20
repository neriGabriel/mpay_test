package com.example.mpaywalletapp

import com.example.mpaywalletapp.data.api.WidgetService
import com.example.mpaywalletapp.data.repository.WidgetRepositoryImpl
import com.example.mpaywalletapp.data.utils.NetworkUtils
import com.example.mpaywalletapp.domain.entity.SimpleWidget
import com.example.mpaywalletapp.domain.entity.Widgets
import com.example.mpaywalletapp.domain.utils.Resource
import com.example.mpaywalletapp.domain.utils.Status
import com.google.common.base.CharMatcher.`is`
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import kotlinx.coroutines.runBlocking
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.Assert.*
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class WidgetRepositoryTest {

    @MockK
    lateinit var widgetRepository: WidgetRepositoryImpl

    @MockK
    lateinit var widgetService: WidgetService

    @MockK
    lateinit var mockedWidgets: Widgets

    @MockK
    lateinit var simpleWidget: SimpleWidget


    @Before
    fun setUp() {
        MockKAnnotations.init(this, true)
        widgetRepository =
            WidgetRepositoryImpl(NetworkUtils.getRetrofitInstance(WidgetService::class.java))

        every { mockedWidgets.widgetList } returns listOf(
            mockk{
                every { identifier } returns "HOME_HEADER_WIDGET"
                every { content.title } returns "Olá Fulano!"
                every { content.cardNumber } returns "•••• •••• •••• 8765"
                every { content.balance } returns null
                every { content.balance } returns null
                every { content.button } returns null
            }
        )
    }

    @Test
    fun `should return success request`() = runBlocking {
        coEvery { widgetService.getWidgets() } returns Response.success(mockedWidgets)
        val response = widgetRepository.getWidgets()
        assertNotNull(response)
        assertTrue(response.status == Status.SUCCESS)
    }

    @Test
    fun `should return a list of SimpleWidget`() = runBlocking {
        coEvery { widgetService.getWidgets() } returns Response.success(mockedWidgets)
        val response = widgetRepository.getWidgets()
        assertNotNull(response.data?.size)
        MatcherAssert.assertThat(response.data?.size, CoreMatchers.`is`(3))
    }

    @Test
    fun `verify if the returned list is instance of SimpleWidget`() = runBlocking {
        coEvery { widgetService.getWidgets() } returns Response.success(mockedWidgets)
        val response = widgetRepository.getWidgets()
        assertNotNull(response.data)
        assertTrue(response.data?.get(0) is SimpleWidget)
    }

    /**
     * This test case will be only valid if retrofit request always returns in the first position
     * an HOME_HEADER_WIDGET, if it returns the position in a dynamic way this should be ignored
     * */
    @Test
    fun `verify if contains home widget`() = runBlocking {
        every { simpleWidget.identifier } returns "HOME_HEADER_WIDGET"

        val response = widgetRepository.getWidgets()
        assertNotNull(response.data)
        assertEquals(response.data?.get(0)?.identifier, simpleWidget.identifier)
    }
}