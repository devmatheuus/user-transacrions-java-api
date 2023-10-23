package br.com.matheus.usertransactionsapi.service.impl;

import br.com.matheus.usertransactionsapi.dto.CreateDepositDto;
import br.com.matheus.usertransactionsapi.dto.UserDto;
import br.com.matheus.usertransactionsapi.exception.AppException;
import br.com.matheus.usertransactionsapi.model.User;
import br.com.matheus.usertransactionsapi.repository.IUserRepository;
import br.com.matheus.usertransactionsapi.service.IUserService;
import br.com.matheus.usertransactionsapi.utils.Utils;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IUserServiceImpl implements IUserService {
    private final IUserRepository userRepository;

    public IUserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    private void checkEmailAndCpf(String cpf, String email) {
        if (userRepository.existsByCpf(cpf)) {
            throw new AppException("CPF already exists", HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(email)) {
            throw new AppException("Email already exists", HttpStatus.BAD_REQUEST);
        }
    }

    public User create(@NotNull UserDto userData) {
        this.checkEmailAndCpf(userData.getCpf(), userData.getEmail());

        User userCreated = new User(userData.getName(), userData.getCpf(), userData.getEmail(), userData.getPassword(), userData.getType());

        return userRepository.save(userCreated);
    }


    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));
    }

    public User update(UserDto user, Long id) {
        this.checkEmailAndCpf(user.getCpf(), user.getEmail());

        final User userFound = userRepository.findById(id).orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        Utils.copyNonNullProperties(user, userFound);

        return userRepository.save(userFound);
    }

    public void delete(Long id) {
        final User userFound = userRepository.findById(id).orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        userRepository.delete(userFound);
    }

    public User createDeposit(@NotNull final CreateDepositDto deposit, final Long id) {
        final User userFound = userRepository.findById(id).orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        userFound.setBalance(userFound.getBalance() + deposit.getValue());

        return userRepository.save(userFound);
    }
}