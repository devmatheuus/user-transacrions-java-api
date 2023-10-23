package br.com.matheus.usertransactionsapi.service;

import br.com.matheus.usertransactionsapi.dto.CreateTransactionDto;
import br.com.matheus.usertransactionsapi.model.Transaction;

import java.util.List;

public interface ITransactionService {
    Transaction createTransaction(final CreateTransactionDto transactionDto);

    List<Transaction> findAll();

    List<Transaction> findUserTransactions(Long id);

    Transaction findById(Long id);
}