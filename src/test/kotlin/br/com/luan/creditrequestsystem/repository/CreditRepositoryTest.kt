package br.com.luan.creditrequestsystem.repository

import br.com.luan.creditrequestsystem.entity.Address
import br.com.luan.creditrequestsystem.entity.Credit
import br.com.luan.creditrequestsystem.entity.Customer
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CreditRepositoryTest {
    @Autowired lateinit var customerRespository: CustomerRepository
    @Autowired lateinit var testEntityManager: TestEntityManager

    private lateinit var customer: Customer
    private lateinit var credit1: Credit
    private lateinit var credit2: Credit

    @BeforeEach fun setup() {
        customer = testEntityManager.persist(buildCustomer())
        credit1 = testEntityManager.persist(buildCredit(customer = customer))
        credit2 = testEntityManager.persist(buildCredit(customer = customer))
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

    private fun buildCredit(
        creditValue: BigDecimal = BigDecimal.valueOf(1234.54),
        dayFirstInstallment: LocalDate = LocalDate.now(),
        numberOfInstallments: Int = 4,
        customer: Customer,
    ) = Credit(
        creditValue = creditValue,
        dayFirstInstallment = dayFirstInstallment,
        numberOfInstallments = numberOfInstallments,
        customer = customer,
    )

}