package be.zlz.budgetor.api.service;

import be.zlz.budgetor.api.domain.Account;
import be.zlz.budgetor.api.domain.Transaction;
import be.zlz.budgetor.api.dto.TransactionDTO;
import be.zlz.budgetor.api.repository.TransactionRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public TransactionDTO upsertTransaction(TransactionDTO transaction) {
        if(transaction.getTimeStamp() == null){
            transaction.setTimeStamp(LocalDate.now());
        }
        Transaction newTransaction = new Transaction(transaction.getAmount(), transaction.getTimeStamp());
        transactionRepository.save(newTransaction);
        return new TransactionDTO(newTransaction);
    }

    public void deleteTransaction(long accId, long id){
        if(accountService.isCurrentOwnerOf(id)){
            transactionRepository.delete(id);
        }
        else {
            throw new AccessDeniedException("You are not the owner of this account");
        }
    }
}
