package br.com.matheus.usertransactionsapi.controller;

import br.com.matheus.usertransactionsapi.dto.CreateDepositDto;
import br.com.matheus.usertransactionsapi.dto.UserDto;
import br.com.matheus.usertransactionsapi.model.User;
import br.com.matheus.usertransactionsapi.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final IUserService IUserService;

    public UserController(IUserService IUserService) {
        this.IUserService = IUserService;
    }

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody final UserDto userData) {
        User user = IUserService.create(userData);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        final List<User> users = IUserService.findAll();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        final User user = IUserService.findById(id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@Valid @RequestBody UserDto user, @PathVariable Long id) {
        final User userUpdated = IUserService.update(user, id);

        return new ResponseEntity<>(userUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        IUserService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<User> createDeposit(@Valid @RequestBody CreateDepositDto value, @PathVariable Long id) {
        final User user = IUserService.createDeposit(value, id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}