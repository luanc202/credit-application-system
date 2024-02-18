package br.com.luan.creditrequestsystem.controller

import br.com.luan.creditrequestsystem.dto.CreditDto
import br.com.luan.creditrequestsystem.dto.CreditView
import br.com.luan.creditrequestsystem.dto.CreditViewList
import br.com.luan.creditrequestsystem.entity.Credit
import br.com.luan.creditrequestsystem.service.imp.CreditService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/credits")
class CreditResource(
    private val creditService: CreditService
) {
    @PostMapping
    fun saveCredit(@RequestBody @Valid creditDto: CreditDto): ResponseEntity<String> {
        val credit: Credit = creditDto.toEntity()
        credit.validDayFirstInstallment()
        val savedCredit: Credit = this.creditService.save(credit)
        return ResponseEntity.status(HttpStatus.CREATED)
            .body("Credit ${credit.creditCode} of Customer ${credit.customer?.firstName} saved")
    }

    @GetMapping
    fun findAllByCustomer(@RequestParam(value = "customerId") customerId: String): ResponseEntity<List<CreditViewList>> {
        val creditViewList = this.creditService.findAllByCustomer(customerId).stream()
            .map { credit: Credit -> CreditViewList(credit) }
            .collect(Collectors.toList())
        return ResponseEntity.status(HttpStatus.OK).body(creditViewList)
    }

    @GetMapping("/{creditCode}")
    fun findByCreditCode(
        @RequestParam(value = "customerId") customerId: String,
        @PathVariable creditCode: String
    ): ResponseEntity<CreditView> {
        val credit: Credit = this.creditService.findByCreditCode(customerId, creditCode)
        return ResponseEntity.status(HttpStatus.OK).body(CreditView(credit))
    }
}