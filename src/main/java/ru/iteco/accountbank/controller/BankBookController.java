package ru.iteco.accountbank.controller;

import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.iteco.accountbank.model.BankBookDto;
import ru.iteco.accountbank.service.BankBookService;

@Slf4j
@RestController
@RequestMapping("/rest/bank-book")
public class BankBookController {

    private final BankBookService bankBookService;

    public BankBookController(BankBookService bankBookService) {
        this.bankBookService = bankBookService;
    }

    @GetMapping({"/{id}", "/"})
    public ResponseEntity<BankBookDto> getBankBookById(@PathVariable Integer id) {
        return ResponseEntity.ok(bankBookService.findById(id));
    }

    @GetMapping("/by-user-id")
    public ResponseEntity<List<BankBookDto>> getBankBookByUserId(@CookieValue Integer userId, @RequestHeader Map<String, String> headers) {
        log.info("Call with headers: {}", headers);
        return ResponseEntity.ok(bankBookService.findByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<BankBookDto> createBankBook(@RequestBody BankBookDto bankBookDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bankBookService.create(bankBookDto));
    }

    @PutMapping
    public BankBookDto updateBankBook(@RequestBody BankBookDto bankBookDto) {
        return bankBookService.update(bankBookDto);
    }

    @DeleteMapping({"/{id}", "/"})
    public void deleteBankBook(@PathVariable Integer id) {
        bankBookService.delete(id);
    }

    @DeleteMapping({"/by-user-id/{id}", "/by-user-id/"})
    public void deleteBankBookByUserId(@PathVariable Integer id) {
        bankBookService.deleteByUserId(id);
    }

}
