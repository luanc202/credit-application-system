package br.com.luan.creditrequestsystem.dto

import br.com.luan.creditrequestsystem.entity.Credit
import br.com.luan.creditrequestsystem.entity.Customer
import org.aspectj.weaver.IntMap
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto (
    val creditValue: BigDecimal,
    val dayFirstOfInstallment: LocalDate,
    val numberOfInstallmetns: Int,
    val customerId: String
) {
    fun toEntity(): Credit = Credit (
        creditValue = this.creditValue,
        dayFirstInstallment = this.dayFirstOfInstallment,
        numberOfInstallments = this.numberOfInstallmetns,
        customer = Customer(id = this.customerId)
    )
}
