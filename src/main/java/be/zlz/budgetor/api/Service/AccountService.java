package be.zlz.budgetor.api.Service;

import be.zlz.budgetor.api.domain.Account;
import be.zlz.budgetor.api.domain.User;
import be.zlz.budgetor.api.repository.AccountRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    private UserService userService;

    private Logger logger;

    @Autowired
    public AccountService(AccountRepository repo, UserService service){
        accountRepository = repo;
        userService = service;
        logger = Logger.getLogger(AccountService.class);
    }

    public List<Account> getAllAccounts(){
        User current = userService.getCurrentUser();
        return accountRepository.findByUserId(current.getId());
    }

    public Account createAccount(){
        return null;
    }
}
