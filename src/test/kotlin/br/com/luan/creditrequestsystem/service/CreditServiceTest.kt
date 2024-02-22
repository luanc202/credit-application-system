package br.com.luan.creditrequestsystem.service;

import br.com.luan.creditrequestsystem.entity.Address
import br.com.luan.creditrequestsystem.entity.Credit
import br.com.luan.creditrequestsystem.entity.Customer
import br.com.luan.creditrequestsystem.repository.CreditRepository
import br.com.luan.creditrequestsystem.repository.CustomerRepository
import br.com.luan.creditrequestsystem.service.imp.CreditService
import br.com.luan.creditrequestsystem.service.imp.CustomerService
import io.mockk.MockKStubScope
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
import java.time.LocalDate
import java.util.*

@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class CreditServiceTest {
    @MockK lateinit var customerRepository: CustomerRepository
    @InjectMockKs lateinit var customerService: CustomerService
    @MockK lateinit var creditRepository: CreditRepository
    @InjectMockKs lateinit var creditService: CreditService

    @Test
    fun `should have credit`() {
        val fakeCustomer: Customer = buildCustomer()
        val fakeCredit: Credit = buildCredit()
        val id: UUID? = fakeCredit.customer?.id
        every { customerRepository.save(any()) } returns fakeCustomer
        every { creditRepository.save(any()) } returns fakeCredit
        every { customerRepository.findById(any()) } returns "asdsaaasdass"
        every { customerService.findById(any()) } returns "asdsaaasdass"
        val actual: Credit = creditService.save(fakeCredit)

        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeCredit)
        verify(exactly = 1) { creditRepository.save(fakeCredit) }
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

    private fun buildCredit(
        creditValue: BigDecimal = BigDecimal.valueOf(1234.54),
        dayFirstInstallment: LocalDate = LocalDate.now(),
        numberOfInstallments: Int = 4,
        customer: Customer = Customer(id = UUID.randomUUID()),
    ) = Credit(
        creditValue = creditValue,
        dayFirstInstallment = dayFirstInstallment,
        numberOfInstallments = numberOfInstallments,
        customer = customer,
    )
}

private infix fun <T, B> MockKStubScope<T, B>.returns(id: String?) {

}
