package be.zlz.budgetor.api.controller;

import be.zlz.budgetor.api.dto.AccountDTO;
import be.zlz.budgetor.api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService service){
        accountService = service;
    }

    @GetMapping("/accounts")
    public List<AccountDTO> getAccounts(){
        List<AccountDTO> accounts = new ArrayList<>();
        accountService.getAllAccounts().forEach(account -> accounts.add(new AccountDTO(account)));
        return accounts;
    }



}
