package com.parliament.chatapp.controller;

import com.parliament.chatapp.model.Account;
import com.parliament.chatapp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/accounts")
    public List<Object> getAllUsers() {
        return accountRepository.getUsers();
    }

    @PostMapping("/accounts")
    public Account create(@Valid @RequestBody Account account) {
        return accountRepository.save(account);
    }

}
