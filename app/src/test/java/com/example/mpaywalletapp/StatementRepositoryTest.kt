package com.example.mpaywalletapp

import com.example.mpaywalletapp.data.api.StatementService
import com.example.mpaywalletapp.data.repository.StatementRepositoryImpl
import com.example.mpaywalletapp.data.utils.NetworkUtils
import com.example.mpaywalletapp.domain.entity.Statement
import com.example.mpaywalletapp.domain.utils.Status
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.Assert
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class StatementRepositoryTest {

    @MockK
    lateinit var statementRepository: StatementRepositoryImpl

    @MockK
    lateinit var statementService: StatementService

    @MockK
    lateinit var mockedStatement: Statement

    @Before
    fun setUp() {
        MockKAnnotations.init(this, true)
        statementRepository = StatementRepositoryImpl(
                NetworkUtils.getRetrofitInstance(StatementService::class.java)
        )

        every { mockedStatement.transactions } returns listOf(
                mockk{
                    every { label } returns "Transferência enviada"
                    every { value } returns "- R\$ 500,00"
                    every { description} returns "Teste fulano ciclano"
                }
        )

        every { mockedStatement.balance } returns mockk {
            every { label } returns "Saldo disponíevl"
            every { value } returns "R\$ 50.000,00"
        }

    }

    @Test
    fun `should return success request`() = runBlocking {
        coEvery { statementService.getStatement("123") } returns Response.success(mockedStatement)
        val response = statementRepository.getStatement("123")
        assertNotNull(response)
        assertTrue(response.status == Status.SUCCESS)
    }

    @Test
    fun `should return error request`() = runBlocking {
        val response = statementRepository.getStatement("456")
        assertTrue(response.status == Status.ERROR)
    }

    @Test
    fun `verify if the returned statement is instance of Statement`() = runBlocking {
        coEvery { statementService.getStatement("123") } returns Response.success(mockedStatement)
        val response = statementRepository.getStatement("123")
        assertNotNull(response)
        assertTrue(response.data is Statement)
    }


}