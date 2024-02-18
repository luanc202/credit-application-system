package br.com.luan.creditrequestsystem.controller

import br.com.luan.creditrequestsystem.dto.CustomerDto
import br.com.luan.creditrequestsystem.dto.CustomerUpdateDto
import br.com.luan.creditrequestsystem.dto.CustomerView
import br.com.luan.creditrequestsystem.service.imp.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/customers")
class CustomerResource(
    private val customerService: CustomerService
) {
    @PostMapping
    fun saveCustomer(@RequestBody customerDto: CustomerDto): ResponseEntity<String> {
        val savedCustomer = this.customerService.save(customerDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body("Customer ${savedCustomer.email} saved.")
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String): ResponseEntity<CustomerView> {
        val customer = this.customerService.findById(id)
        return ResponseEntity.status(HttpStatus.OK).body(CustomerView(customer))
    }

    @DeleteMapping("/{id}")
    fun deleteCustomer(@PathVariable id: String) =
        this.customerService.delete(id)

    @PatchMapping
    fun updateCustomer(
        @RequestParam(value = "customerId") id: String,
        @RequestBody customerUpdateDto: CustomerUpdateDto
    ): ResponseEntity<CustomerView> {
        val customer = this.customerService.findById(id)
        val customerToUpdate = customerUpdateDto.toEntity(customer)
        val customerUpdated = this.customerService.save(customerToUpdate)
        return ResponseEntity.status(HttpStatus.OK).body(CustomerView(customerUpdated))
    }
}