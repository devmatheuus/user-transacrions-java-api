package br.com.matheus.usertransactionsapi.controller;

import br.com.matheus.usertransactionsapi.dto.CreateTransactionDto;
import br.com.matheus.usertransactionsapi.model.Transaction;
import br.com.matheus.usertransactionsapi.service.ITransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    final private ITransactionService ITransactionService;

    public TransactionController(ITransactionService ITransactionService) {
        this.ITransactionService = ITransactionService;
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@Valid @RequestBody CreateTransactionDto transactionDto) {
        Transaction transactionCreated = ITransactionService.createTransaction(transactionDto);

        return new ResponseEntity<>(transactionCreated, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> findAll() {
        List<Transaction> transactions = ITransactionService.findAll();

        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Transaction>> findUserTransactions(@PathVariable Long id) {
        List<Transaction> transactions = ITransactionService.findUserTransactions(id);

        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<Transaction> findById(@PathVariable Long id) {
        Transaction transaction = ITransactionService.findById(id);

        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }
}