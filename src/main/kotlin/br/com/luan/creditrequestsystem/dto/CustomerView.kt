package br.com.luan.creditrequestsystem.dto

import br.com.luan.creditrequestsystem.entity.Customer
import java.math.BigDecimal

class CustomerView(
    val firstName: String,
    val lastName: String,
    val cpf: String,
    val income: BigDecimal,
    val zipCode: String,
    val street: String,
) {
    constructor(customer: Customer) : this(
        firstName = customer.firstName,
        lastName = customer.lastName,
        cpf = customer.cpf,
        income = customer.income,
        zipCode = customer.address.zipCode,
        street = customer.address.street,
    )
}
