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
    @Column(nullable = false, unique = true) var creditCode: UUID = UUID.randomUUID(),
    @Column(nullable = false) val creditValue: BigDecimal = BigDecimal.ZERO,
    @Column(nullable = false) val dayFirstInstallment: LocalDate,
    @Column(nullable = false) var numberOfInstallments: Int = 0,
    @Enumerated val status: Status = Status.IN_PROGRESS,
    @ManyToOne var customer: Customer? = null,
    @Id @GeneratedValue(strategy = GenerationType.UUID) val id: UUID? = null,
) {
    fun validDayFirstInstallment(): Boolean {
        return if (this.dayFirstInstallment.isAfter(LocalDate.now().plusMonths(3))) true
        else throw BusinessException("Invalid first day of installment for credit, must be at least 3 months from now.")
    }
}
