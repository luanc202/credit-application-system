package br.com.luan.creditrequestsystem.dto

import br.com.luan.creditrequestsystem.entity.Address
import br.com.luan.creditrequestsystem.entity.Customer
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal

class CustomerDto(
    @field:NotEmpty(message = "First name must not be empty") val firstName: String,
    @field:NotEmpty(message = "Last name must not be empty") val lastName: String,
    @field:CPF(message = "CPF must be valid") val cpf: String,
    @field:NotNull(message = "Invalid input") val income: BigDecimal,
    @field:Email(message = "Email must be valid") val email: String,
    @field:NotEmpty(message = "Password must not be empty") val password: String,
    @field:NotEmpty(message = "Zip code must not be empty") val zipCode: String,
    @field:NotEmpty(message = "Street must not be empty") val street: String
) {
    fun toEntity(): Customer = Customer(
        firstName = this.firstName,
        lastName = this.lastName,
        cpf = this.cpf,
        income = this.income,
        email = this.email,
        password = this.password,
        address = Address(
            zipCode = this.zipCode,
            street = this.street,
        )
    )
}
