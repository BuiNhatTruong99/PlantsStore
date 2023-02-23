package com.datamining.rest;

import com.datamining.DTO.AccountDTO;
import com.datamining.entity.Account;
import com.datamining.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/account")
public class AccountRest {
    @Autowired
    AccountService accountService;

    @GetMapping()
    public ObjectResponse getAll() {
        List<Account> accounts = accountService.findAll();
        List<AccountDTO> accountsDTO = accounts.stream()
                .map(AccountDTO::convert)
                .collect(Collectors.toList());
        return new ObjectResponse("success", accountsDTO, HttpStatus.OK.value());
    }

    @GetMapping("/{id}")
    public ObjectResponse getOne(@PathVariable("id") Integer id) {
        Account account = accountService.findById(id);
        AccountDTO accountDTO = AccountDTO.convert(account);
        return new ObjectResponse("success", accountDTO, HttpStatus.OK.value());
    }



    @PutMapping("/{id}")
    public ObjectResponse update(@PathVariable("id") Integer id, @RequestBody Account account) {
        Account account1 = accountService.update(account);
        AccountDTO accountDTO = AccountDTO.convert(account1);
        return new ObjectResponse("success", accountDTO, HttpStatus.OK.value());
    }

}
