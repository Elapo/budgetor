package be.zlz.budgetor.api.service;

import be.zlz.budgetor.api.domain.Account;
import be.zlz.budgetor.api.dto.TransactionDTO;
import be.zlz.budgetor.api.repository.TransactionRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;

    private AccountService accountService;

    private Logger logger;

    @Autowired
    public TransactionService(AccountService accountService, TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
        this.logger = Logger.getLogger(TransactionService.class);
    }

    public List<TransactionDTO> getTransactionsForAccount(long id) {
        Account account = accountService.getAccountById(id);

        List<TransactionDTO> transactionDTOS = new ArrayList<>();

        if (account != null) {
            transactionRepository.findByAccount(account).forEach(
                    transaction -> transactionDTOS.add(new TransactionDTO(transaction))
            );
        }

        return transactionDTOS;
    }
}
