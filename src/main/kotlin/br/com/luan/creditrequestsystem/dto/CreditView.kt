package br.com.luan.creditrequestsystem.dto

import br.com.luan.creditrequestsystem.entity.Credit
import br.com.luan.creditrequestsystem.enummeration.Status
import java.math.BigDecimal
import java.util.UUID

data class CreditView(
    val creditCode: String,
    val creditValue: BigDecimal,
    val numberOfInstallments: Int,
    val status: Status,
    val emailCustomer: String?,
    val incomeCustomer: BigDecimal?
) {
    constructor(credit: Credit): this (
        creditCode = credit.creditCode,
        creditValue = credit.creditValue,
        numberOfInstallments = credit.numberOfInstallments,
        status = credit.status,
        emailCustomer = credit.customer?.email,
        incomeCustomer = credit.customer?.income,
    )
}
