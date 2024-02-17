package br.com.luan.creditrequestsystem.service

import br.com.luan.creditrequestsystem.entity.Customer

interface ICustomerService {
    fun save(customer: Customer): Customer
    fun findById(id: String): Customer
    fun delete(id: String)
}