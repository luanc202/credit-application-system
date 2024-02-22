package br.com.luan.creditrequestsystem.service

import br.com.luan.creditrequestsystem.entity.Address
import br.com.luan.creditrequestsystem.entity.Customer
import br.com.luan.creditrequestsystem.exception.BusinessException
import br.com.luan.creditrequestsystem.repository.CustomerRepository
import br.com.luan.creditrequestsystem.service.imp.CustomerService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.util.*

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

    @Test
    fun `should find customer by id` () {
        val fakeId: UUID = UUID.randomUUID();
        val fakeCustomer: Customer = buildCustomer(id = fakeId)
        every { customerRepository.findById(fakeId.toString()) } returns Optional.of(fakeCustomer)

        val actual: Customer = customerService.findById(fakeId)
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeCustomer)
        Assertions.assertThat(actual).isExactlyInstanceOf(Customer::class.java)
        verify(exactly = 1) { customerRepository.findById(fakeId.toString()) }
    }

    @Test
    fun `should not find customer by invalid id and throw BusinessException` () {
        val fakeId: UUID = UUID.randomUUID();

        every { customerRepository.findById(fakeId.toString()) } returns Optional.empty()

        Assertions.assertThatExceptionOfType(BusinessException::class.java)
            .isThrownBy { customerService.findById(fakeId) }
            .withMessage("Id $fakeId not found")
        verify(exactly = 1) { customerRepository.findById(fakeId.toString()) }
    }

    @Test
    fun `should delete a customer by id` () {
        val fakeId: UUID = UUID.randomUUID();
        val fakeCustomer: Customer = buildCustomer(id = fakeId)
        every { customerRepository.findById(fakeId.toString()) } returns Optional.of(fakeCustomer)
        every { customerRepository.delete(fakeCustomer) } just runs

        customerService.delete(fakeId)

        verify(exactly = 1) { customerRepository.findById(fakeId.toString()) }
        verify(exactly = 1) { customerRepository.delete(fakeCustomer) }
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
        id: UUID = UUID.randomUUID()
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