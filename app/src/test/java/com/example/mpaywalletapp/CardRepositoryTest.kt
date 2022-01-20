package com.example.mpaywalletapp

import com.example.mpaywalletapp.data.api.CardService
import com.example.mpaywalletapp.data.repository.CardRepositoryImpl
import com.example.mpaywalletapp.data.utils.NetworkUtils
import com.example.mpaywalletapp.domain.entity.Card
import com.example.mpaywalletapp.domain.utils.Resource
import com.example.mpaywalletapp.domain.utils.Status
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class CardRepositoryTest {

    @MockK
    lateinit var cardRepository: CardRepositoryImpl

    @MockK
    lateinit var cardService: CardService

    @MockK
    lateinit var mockedCard: Card

    @Before
    fun setUp() {
        MockKAnnotations.init(this, true)
        cardRepository = CardRepositoryImpl(NetworkUtils.getRetrofitInstance((CardService::class.java)))
        mockedCard = mockk {
            every { cardNumber } returns "•••• •••• •••• 8765"
            every { cardName } returns "Teste Fulano Ciclano"
            every { expirationDate } returns "09/25"
            every { availableLimit } returns "R\$ 3.000,00"
            every { totalLimit } returns "R\$ 5.000,00"
        }
    }

    @Test
    fun `should return success request`() = runBlocking {
        coEvery { cardService.getCard("123") } returns Response.success(mockedCard)
        val response = cardRepository.getCard("123")
        Assert.assertNotNull(response)
        assertTrue(response.status == Status.SUCCESS)
    }

    @Test
    fun `should return error request`() = runBlocking {
        val response = cardRepository.getCard("456")
        assertTrue(response.status == Status.ERROR)
    }

    @Test
    fun `verify if the returned card is instance of Card`() = runBlocking {
        coEvery { cardService.getCard("123") } returns Response.success(mockedCard)
        val response = cardRepository.getCard("123")
        Assert.assertNotNull(response)
        assertTrue(response.data is Card)
    }
}