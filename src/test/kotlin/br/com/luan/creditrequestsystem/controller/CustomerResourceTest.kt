package br.com.luan.creditrequestsystem.controller

import br.com.luan.creditrequestsystem.dto.CustomerDto
import br.com.luan.creditrequestsystem.dto.CustomerUpdateDto
import br.com.luan.creditrequestsystem.repository.CustomerRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.math.BigDecimal

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@ContextConfiguration
class CustomerResourceTest {
    @Autowired private lateinit var customerRepository: CustomerRepository
    @Autowired private lateinit var mockMvc: MockMvc
    @Autowired private lateinit var objectMapper: ObjectMapper

    companion object {
        const val URL: String = "/api/customers"
    }

    @BeforeEach fun setup() = customerRepository.deleteAll()

    @AfterEach fun tearDown() = customerRepository.deleteAll()

    @Test
    fun `should create a customer and return 201 status`() {
        val customerDto: CustomerDto = builderCustomerDto()
        val valueAsString: String = objectMapper.writeValueAsString(customerDto)

        mockMvc.perform(MockMvcRequestBuilders
            .post(URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(valueAsString))
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Luan"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Sobrenome"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value("85048101006"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("luan@sales.com"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.income").value("2000.0"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.zipCode").value("12345678"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.street").value("Rua do Lado, Av. Norte"))
            .andDo(MockMvcResultHandlers.print())

    }

    private fun builderCustomerDto(
        firstName: String = "Luan",
        lastName: String = "Sobrenome",
        cpf: String = "85048101006",
        email: String = "luan@sales.com",
        income: BigDecimal = BigDecimal.valueOf(2000.0),
        password: String = "1234",
        zipCode: String = "12345678",
        street: String = "Rua do Lado, Av. Norte",
    ) = CustomerDto(
        firstName = firstName,
        lastName = lastName,
        cpf = cpf,
        email = email,
        income = income,
        password = password,
        zipCode = zipCode,
        street = street
    )

    private fun builderCustomerUpdateDto(
        firstName: String = "Luan Atualizado",
        lastName: String = "Sobrenome Atualizado",
        income: BigDecimal = BigDecimal.valueOf(6000.0),
        zipCode: String = "77777777",
        street: String = "Rua Atualizada"
    ): CustomerUpdateDto = CustomerUpdateDto(
        firstName = firstName,
        lastName = lastName,
        income = income,
        zipCode = zipCode,
        street = street
    )
}