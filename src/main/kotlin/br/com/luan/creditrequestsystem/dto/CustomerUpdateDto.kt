package br.com.luan.creditrequestsystem.dto

import br.com.luan.creditrequestsystem.entity.Customer
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class CustomerUpdateDto(
    @field:NotEmpty(message = "First name must not be empty") val firstName: String,
    @field:NotEmpty(message = "Last name must not be empty") val lastName: String,
    @field:NotNull(message = "Invalid input") val income: BigDecimal,
    @field:NotEmpty(message = "Password must not be empty") val zipCode: String,
    @field:NotEmpty(message = "Zip code must not be empty") val street: String
) {
    fun toEntity(customer: Customer): Customer {
        customer.firstName = this.firstName
        customer.lastName = this.lastName
        customer.income = this.income
        customer.address.zipCode = this.zipCode
        customer.address.street = this.street
        return customer
    }
}
