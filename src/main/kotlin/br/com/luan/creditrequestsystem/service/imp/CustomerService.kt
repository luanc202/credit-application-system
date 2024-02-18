package br.com.luan.creditrequestsystem.service.imp

import br.com.luan.creditrequestsystem.entity.Customer
import br.com.luan.creditrequestsystem.repository.CustomerRepository
import br.com.luan.creditrequestsystem.service.ICustomerService
import br.com.luan.creditrequestsystem.exception.BusinessException
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
) : ICustomerService {

    override fun save(customer: Customer): Customer = this.customerRepository.save(customer)


    override fun findById(id: String): Customer = this.customerRepository.findById(id).orElseThrow {
        throw BusinessException("Id $id not found")
    }


    override fun delete(id: String) {
        val customer: Customer = this.findById(id)
        this.customerRepository.delete(customer)
    }
}