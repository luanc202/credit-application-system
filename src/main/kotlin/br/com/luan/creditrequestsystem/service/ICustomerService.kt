package br.com.luan.creditrequestsystem.service

import br.com.luan.creditrequestsystem.entity.Customer
import java.util.UUID

interface ICustomerService {
    fun save(customer: Customer): Customer
    fun findById(id: UUID): Customer
    fun delete(id: UUID)
}