package com.datamining.servide.impl;

import com.datamining.dao.AccountDAO;
import com.datamining.entity.Account;
import com.datamining.entity.Profile;
import com.datamining.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountDAO accountDAO;
    @Autowired
    BCryptPasswordEncoder pe;


    @Override
    public List<Account> findAll() {
        return accountDAO.findAll();
    }

    @Override
    public Account findById(Integer id) {
        return accountDAO.findById(id).get();
    }
    
	@Override
	public Account findByTk(String username) {
		return accountDAO.findByTk(username); // Đã trùng
	}
	
	@Override
	public Account create(Account account) {
		return accountDAO.save(account);
	}

    @Override
    public Account update(Account account) {
        return accountDAO.save(account);
    }

    @Override
    public void delete(Integer id) {
        accountDAO.deleteById(id);
    }

    @Override
    public List<Account> findByUsername(String username) {
        return accountDAO.findByUsername(username);
    }

    @Override
    public void loginFromOAuth2(OAuth2AuthenticationToken oauth2) {
        String email = oauth2.getPrincipal().getAttribute("email");
        String password = Long.toHexString(System.currentTimeMillis());

        UserDetails user = User.withUsername(email)
                .password(pe.encode(password)).roles("GUEST").build();
        Authentication auth = new UsernamePasswordAuthenticationToken(user, null,user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }


}
