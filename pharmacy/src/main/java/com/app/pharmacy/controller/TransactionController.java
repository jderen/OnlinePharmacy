package com.app.pharmacy.controller;

import com.app.pharmacy.model.Transaction;
import com.app.pharmacy.model.dao.TransactionDao;
import com.app.pharmacy.model.dto.TransactionDto;
import com.app.pharmacy.model.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    private final TransactionDao transactionDao;

    @Autowired
    public TransactionController(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    @GetMapping("/all")
    public List<TransactionDto> findAll() {
        List<Transaction> transactions = transactionDao.findAll();
        return transactions
                .stream()
                .map(TransactionDto::getTransactionDtoByTransaction)
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public TransactionDto addTransaction(TransactionDto transactionDto){
        Transaction transaction= TransactionDto.getTransactionByTransactionDto(transactionDto);
        return TransactionDto.getTransactionDtoByTransaction(transactionDao.insert(transaction));
    }

    @PostMapping("{id}/accept")
    public void changeTransactionStatusToStatusAcceptedByTransactionId(@PathVariable Long id){
        Transaction transaction = transactionDao.findById(id).orElseThrow(NullPointerException::new);
        transaction.setStatus(Status.ACCEPTED);
        transactionDao.update(transaction);
    }

    @PostMapping("{id}/receive")
    public void changeTransactionStatusToStatusReceivedByTransactionId(@PathVariable Long id){
        Transaction transaction = transactionDao.findById(id).orElseThrow(NullPointerException::new);
        transaction.setStatus(Status.RECEIVED);
        transactionDao.update(transaction);
    }

    @PostMapping("{id}/realise")
    public void changeTransactionStatusToStatusRealisedByTransactionId(@PathVariable Long id){
        Transaction transaction = transactionDao.findById(id).orElseThrow(NullPointerException::new);
        transaction.setStatus(Status.REALISED);
        transactionDao.update(transaction);
    }

    @PostMapping("{id}/cancel")
    public void changeTransactionStatusToStatusCancelledByTransactionId(@PathVariable Long id){
        Transaction transaction = transactionDao.findById(id).orElseThrow(NullPointerException::new);
        transaction.setStatus(Status.CANCELLED);
        transactionDao.update(transaction);
    }
}
