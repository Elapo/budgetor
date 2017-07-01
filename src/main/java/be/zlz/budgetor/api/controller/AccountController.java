package be.zlz.budgetor.api.controller;

import be.zlz.budgetor.api.dto.AccountDTO;
import be.zlz.budgetor.api.dto.TransactionDTO;
import be.zlz.budgetor.api.service.AccountService;
import be.zlz.budgetor.api.service.TransactionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    private TransactionService transactionService;

    private Logger logger;

    @Autowired
    public AccountController(AccountService service, TransactionService transactionService) {
        accountService = service;
        this.transactionService = transactionService;
        logger = Logger.getLogger(AccountController.class);
    }

    @GetMapping
    public List<AccountDTO> getAccounts() {
        List<AccountDTO> accounts = new ArrayList<>();
        accountService.getAllAccounts().forEach(account -> accounts.add(new AccountDTO(account)));
        return accounts;
    }

    @PutMapping
    public AccountDTO upsertAccount(@RequestBody AccountDTO account) {
        return new AccountDTO(accountService.upsertAccount(account));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable int id) {
        logger.debug("Trying to delete account no." + id);
        accountService.deleteAccount(id);
    }

    @GetMapping("/{id}/transactions")
    public List<TransactionDTO> getTransactions(@PathVariable int id) {
        return transactionService.getTransactionsForAccount(id);
    }

}
