package br.com.matheus.usertransactionsapi.repository;

import br.com.matheus.usertransactionsapi.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByPayerId(Long id);
}