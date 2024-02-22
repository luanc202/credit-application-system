package br.com.luan.creditrequestsystem.dto

import br.com.luan.creditrequestsystem.entity.Customer
import java.math.BigDecimal
import java.util.UUID

class CustomerView(
    val firstName: String,
    val lastName: String,
    val cpf: String,
    val income: BigDecimal,
    val zipCode: String,
    val email: String,
    val street: String,
    val id: UUID?
) {
    constructor(customer: Customer) : this(
        firstName = customer.firstName,
        lastName = customer.lastName,
        cpf = customer.cpf,
        email = customer.email,
        income = customer.income,
        zipCode = customer.address.zipCode,
        street = customer.address.street,
        id = customer.id
    )
}
