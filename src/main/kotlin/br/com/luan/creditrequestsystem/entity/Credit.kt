package br.com.luan.creditrequestsystem.entity

import br.com.luan.creditrequestsystem.enummeration.Status
import br.com.luan.creditrequestsystem.exception.BusinessException
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

@Entity
@Table(name = "Credit")
data class Credit(
    @Column(nullable = false, unique = true) val creditCode: String = UUID.randomUUID().toString(),
    val creditValue: BigDecimal = BigDecimal.ZERO,
    val dayFirstInstallment: LocalDate,
    var numberOfInstallments: Int = 0,
    @Enumerated val status: Status = Status.IN_PROGRESS,
    @ManyToOne var customer: Customer? = null,
    @Id @GeneratedValue(strategy = GenerationType.UUID) val id: String? = null,
) {
    fun validDayFirstInstallment(): Boolean {
        return if (this.dayFirstInstallment.isAfter(LocalDate.now().plusMonths(3))) true
        else throw BusinessException("Invalid first day of installment for credit, must be at least 3 months from now.")
    }
}
