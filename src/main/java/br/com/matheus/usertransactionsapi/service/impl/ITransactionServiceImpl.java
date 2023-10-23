package br.com.matheus.usertransactionsapi.service.impl;

import br.com.matheus.usertransactionsapi.dto.CreateTransactionDto;
import br.com.matheus.usertransactionsapi.exception.AppException;
import br.com.matheus.usertransactionsapi.model.Transaction;
import br.com.matheus.usertransactionsapi.model.User;
import br.com.matheus.usertransactionsapi.repository.ITransactionRepository;
import br.com.matheus.usertransactionsapi.repository.IUserRepository;
import br.com.matheus.usertransactionsapi.service.ITransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ITransactionServiceImpl implements ITransactionService {

    final private ITransactionRepository transactionRepository;
    final private IUserRepository userRepository;

    public ITransactionServiceImpl(ITransactionRepository transactionRepository, IUserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }


    public Transaction createTransaction(final CreateTransactionDto transactionDto) {
        final User foundPayer = userRepository.findById(transactionDto.getPayer_id()).orElseThrow(() -> new AppException("Payer not found", HttpStatus.NOT_FOUND));
        final User foundPayee = userRepository.findById(transactionDto.getPayee_id()).orElseThrow(() -> new AppException("Payer not found", HttpStatus.NOT_FOUND));

        if (Objects.equals(foundPayer.getType(), "SELLER")) {
            throw new AppException("Seller can't be payer", HttpStatus.BAD_REQUEST);
        }

        if (foundPayer.getBalance() < transactionDto.getValue()) {
            throw new AppException("Payer has no balance", HttpStatus.BAD_REQUEST);
        } else {
            foundPayer.setBalance(foundPayer.getBalance() - transactionDto.getValue());
            foundPayee.setBalance(foundPayee.getBalance() + transactionDto.getValue());

            userRepository.save(foundPayer);
            userRepository.save(foundPayee);
        }

        final Transaction transaction = new Transaction(foundPayer, foundPayee, transactionDto.getValue());

        return transactionRepository.save(transaction);
    }


    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public List<Transaction> findUserTransactions(Long id) {
        return transactionRepository.findByPayerId(id);
    }

    public Transaction findById(Long id) {
        return transactionRepository.findById(id).orElseThrow(() -> new AppException("Transaction not found", HttpStatus.NOT_FOUND));
    }
}