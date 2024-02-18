package br.com.luan.creditrequestsystem.dto

import br.com.luan.creditrequestsystem.entity.Credit
import br.com.luan.creditrequestsystem.entity.Customer
import jakarta.validation.constraints.Future
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto (
    @field:NotNull(message = "Credit value must not be null") val creditValue: BigDecimal,
    @field:Future(message = "First day of installment must be in the future") val dayFirstOfInstallment: LocalDate,
    @field:NotNull @field:Positive(message = "Number of installments must be at least 1 or greater integer") val numberOfInstallments: Int,
    @field:NotEmpty(message = "Customer id must not be empty") val customerId: String
) {
    fun toEntity(): Credit = Credit (
        creditValue = this.creditValue,
        dayFirstInstallment = this.dayFirstOfInstallment,
        numberOfInstallments = this.numberOfInstallments,
        customer = Customer(id = this.customerId)
    )
}
