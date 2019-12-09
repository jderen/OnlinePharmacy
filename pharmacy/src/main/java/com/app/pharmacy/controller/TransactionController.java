package com.app.pharmacy.controller;

import com.app.pharmacy.model.Product;
import com.app.pharmacy.model.Transaction;
import com.app.pharmacy.model.dao.ProductDao;
import com.app.pharmacy.model.dao.TransactionDao;
import com.app.pharmacy.model.dto.ProductDto;
import com.app.pharmacy.model.dto.TransactionDto;
import com.app.pharmacy.model.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Inject
    ProductDao productDao;

    private final TransactionDao transactionDao;

    @Autowired
    public TransactionController(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    @GetMapping("/all")
    public List<TransactionDto> findAll() {
        List<Transaction> transactions = transactionDao.findAll();
        List<TransactionDto> transactionDtos = transactions
                .stream()
                .map(TransactionDto::getTransactionDtoByTransaction)
                .collect(Collectors.toList());
        transactionDtos.forEach(transactionDto -> {
            List<Product> products = new ArrayList<>();
            List<ProductDto> productDtos = new ArrayList<>();
            transactionDto.getProductIds().
                    forEach(id -> products.add(productDao.findById(id).orElseThrow(NullPointerException::new)));
            productDtos = products.stream().map(ProductDto::getProductDtoByProduct).collect(Collectors.toList());
            transactionDto.setProductsAll(productDtos);
        });
        return transactionDtos;
    }

    @PostMapping("/add")
    public TransactionDto addTransaction(@RequestBody TransactionDto transactionDto){
        List <Product> products = new ArrayList<>();
        transactionDto.getProductIds().forEach(id ->
                products.add(productDao.findById(id).orElseThrow(NullPointerException::new))
        );
        Transaction transaction= TransactionDto.getTransactionByTransactionDto(transactionDto);
        transaction.setProducts(products);
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
