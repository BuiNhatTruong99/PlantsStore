package com.datamining.service;

import com.datamining.entity.Account;
import com.datamining.entity.Profile;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import java.util.List;

public interface AccountService {

    List<Account> findAll();

    Account findById(Integer id);
    
    Account findByTk(String username);
    
	Account create(Account account);

    Account update(Account account);

    void delete(Integer id);

    List<Account> findByUsername(String username);

    void loginFromOAuth2(OAuth2AuthenticationToken oauth2);
}
