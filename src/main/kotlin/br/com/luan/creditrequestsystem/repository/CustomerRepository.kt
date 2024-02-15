package br.com.luan.creditrequestsystem.repository

import br.com.luan.creditrequestsystem.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository: JpaRepository<Customer, String> {
}