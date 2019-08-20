package com.restapi.smart.security;

import com.restapi.smart.domain.Account;
import com.restapi.smart.domain.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class AccountContextService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(AccountContextService.class);

    @Autowired
    private AccountRepository accountRepository;


    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        log.info("loadUserByUsername:"+userId);

        Account account = accountRepository.findByUserId(userId).orElseThrow(() -> new NoSuchElementException("아이디에 맞는 계정이 없습니다."));

        return getAccountContext(account);
    }

    private AccountContext getAccountContext(Account account) {
        return AccountContext.fromAccountModel(account);
    }
}