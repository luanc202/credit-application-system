package br.com.luan.creditrequestsystem.entity

import br.com.luan.creditrequestsystem.enummeration.Status
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

data class Credit (
    val creditCode: UUID = UUID.randomUUID(),
    val creditValue: BigDecimal = BigDecimal.ZERO,
    val dayFirstInstallment: LocalDate,
    var numberOfInstallments: Int = 0,
    val status: Status = Status.IN_PROGRESS,
    val customer: Customer? = null,
    val id: Long? = null,
)
