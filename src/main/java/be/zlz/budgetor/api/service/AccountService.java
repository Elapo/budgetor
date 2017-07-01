package be.zlz.budgetor.api.service;

import be.zlz.budgetor.api.domain.Account;
import be.zlz.budgetor.api.domain.User;
import be.zlz.budgetor.api.dto.AccountDTO;
import be.zlz.budgetor.api.repository.AccountRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    private UserService userService;

    private Logger logger;

    @Autowired
    public AccountService(AccountRepository repo, UserService service) {
        accountRepository = repo;
        userService = service;
        logger = Logger.getLogger(AccountService.class);
    }

    public List<Account> getAllAccounts() {
        User current = userService.getCurrentUser();
        return accountRepository.findByUserId(current.getId());
    }

    public Account getAccountById(long id) {
        return accountRepository.findOne(id);
    }

    public Account upsertAccount(AccountDTO account) {
        Account newAccount = new Account(account.getCurrentValue(), account.getNickName(), userService.getCurrentUser());
        accountRepository.save(newAccount);
        return newAccount;
    }

    public void deleteAccount(long id) {
        Account account = accountRepository.findOne(id);
        List<Account> userAccounts = accountRepository.findByUserId(userService.getCurrentUser().getId());

        if (userAccounts.contains(account)) {
            accountRepository.delete(id);
        } else {
            throw new AccessDeniedException("You are not the owner of this account");
        }
    }

}
