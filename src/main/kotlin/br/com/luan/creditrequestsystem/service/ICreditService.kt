package br.com.luan.creditrequestsystem.service

import br.com.luan.creditrequestsystem.entity.Credit
import java.util.UUID

interface ICreditService {
    fun save(credit: Credit): Credit
    fun findAllByCustomer(customerId: UUID): List<Credit>
    fun findByCreditCode(customerId: UUID, creditCode: UUID): Credit
}