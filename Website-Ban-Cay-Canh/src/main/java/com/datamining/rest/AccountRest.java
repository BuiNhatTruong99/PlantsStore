package com.datamining.rest;

import com.datamining.DTO.AccountDTO;
import com.datamining.DTO.ProductDTO;
import com.datamining.entity.Account;
import com.datamining.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    @DeleteMapping("/{id}")
    public ObjectResponse delete(@PathVariable("id") Integer id) {
        accountService.delete(id);
        return new ObjectResponse("success", null, HttpStatus.OK.value());
    }

    @PostMapping("/registered")
        public ObjectResponse register(@RequestBody Account account) {
        Account account1 = accountService.create(account);
        AccountDTO accountDTO = AccountDTO.convert(account1);
        return new ObjectResponse("success", accountDTO, HttpStatus.OK.value());
    }

    @GetMapping("/search/{username}")
    public ResponseEntity<ObjectResponse> search(@PathVariable("username") String username) {
        try {
            var account = accountService.findByUsername(username);
            var accountDTO = account.stream()
                    .map(AccountDTO::convert)
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("success", accountDTO, HttpStatus.OK.value()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ObjectResponse("error", e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

}
