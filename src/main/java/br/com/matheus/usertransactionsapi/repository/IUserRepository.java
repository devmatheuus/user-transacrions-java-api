package br.com.matheus.usertransactionsapi.repository;

import br.com.matheus.usertransactionsapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);
}