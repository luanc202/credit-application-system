package br.com.luan.creditrequestsystem.service

import br.com.luan.creditrequestsystem.entity.Address
import br.com.luan.creditrequestsystem.entity.Customer
import br.com.luan.creditrequestsystem.repository.CreditRepository
import br.com.luan.creditrequestsystem.repository.CustomerRepository
import br.com.luan.creditrequestsystem.service.imp.CustomerService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.util.UUID

@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class CustomerServiceTest {

    @MockK lateinit var customerRepository: CustomerRepository
    @InjectMockKs lateinit var customerService: CustomerService

    @Test
    fun `should have customer`() {

        val fakeCustomer: Customer = buildCustomer()
        every { customerRepository.save(any()) } returns fakeCustomer

        val actual: Customer = customerService.save(fakeCustomer)

        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeCustomer)
        verify(exactly = 1) { customerRepository.save(fakeCustomer) }
    }

    private fun buildCustomer(
        firstName: String = "Pedro",
        lastName: String = "Sales",
        cpf: String = "85048101006",
        email: String = "pedro@sales.com",
        password: String = "12345",
        zipCode: String = "12345678",
        street: String = "Rua do Lado, Av. Norte",
        income: BigDecimal = BigDecimal.valueOf(1000.0),
        id: String = UUID.randomUUID().toString()
    ) = Customer(
        firstName = firstName,
        lastName = lastName,
        cpf = cpf,
        email = email,
        password = password,
        address = Address(zipCode = zipCode, street = street),
        income = income,
        id = id
    )
}