package br.com.matheus.usertransactionsapi.service;

import br.com.matheus.usertransactionsapi.dto.CreateDepositDto;
import br.com.matheus.usertransactionsapi.dto.UserDto;
import br.com.matheus.usertransactionsapi.model.User;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IUserService {
    User create(@NotNull UserDto userData);

    List<User> findAll();

    User findById(Long id);

    User update(UserDto user, Long id);

    void delete(Long id);

    User createDeposit(@NotNull final CreateDepositDto deposit, final Long id);
}