package br.com.luan.creditrequestsystem.repository

import br.com.luan.creditrequestsystem.entity.Credit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CreditRepository: JpaRepository<Credit, String> {
    fun findByCreditCode(creditCode: UUID) : Credit?

    @Query(value = "select * from credit where customer_id = ?1", nativeQuery = true)
    fun findAllByCustomerId(customerId: String): List<Credit>
}