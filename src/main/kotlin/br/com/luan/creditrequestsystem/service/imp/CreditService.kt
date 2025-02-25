package br.com.luan.creditrequestsystem.service.imp

import br.com.luan.creditrequestsystem.entity.Credit
import br.com.luan.creditrequestsystem.repository.CreditRepository
import br.com.luan.creditrequestsystem.service.ICreditService
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CreditService(
    private val creditRepository: CreditRepository,
    private val customerService: CustomerService
): ICreditService {
    override fun save(credit: Credit): Credit {
        credit.apply {
            customer = customerService.findById(credit.customer?.id!!)
        }
        return this.creditRepository.save(credit)
    }

    override fun findAllByCustomer(customerId: UUID): List<Credit> {
        return this.creditRepository.findAllByCustomerId(customerId)
    }

    override fun findByCreditCode(customerId: UUID, creditCode: UUID): Credit {
        val credit: Credit = this.creditRepository.findByCreditCode(creditCode) ?: throw RuntimeException("Credit $creditCode not found.")
        return if (credit.customer?.id == customerId) credit else throw IllegalArgumentException("Error finding credit, Contact admin.")
    }
}