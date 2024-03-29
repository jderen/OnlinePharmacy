package com.app.pharmacy.model.dto;

import com.app.pharmacy.model.Account;
import com.app.pharmacy.model.Transaction;
import com.app.pharmacy.model.User;
import com.app.pharmacy.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDto {
    private Long id;
    private Long number;
    private String status;
    private Long userId;
    private Boolean prescriptionRequired;
    private List<Long> productIds;
    private List<ProductDto> productsAll;


    public static Transaction getTransactionByTransactionDto(TransactionDto transactionDto){
        return Transaction.builder()
                .id(transactionDto.id)
                .number(transactionDto.number)
                .status(Status.valueOf(transactionDto.getStatus()))
                .user(new User(transactionDto.userId))
                .prescriptionRequired(transactionDto.prescriptionRequired)
                .prescriptions(new ArrayList<>())
                .products(new ArrayList<>())
                .build();
    }

    public static TransactionDto getTransactionDtoByTransaction(Transaction transaction){
        List<Long> ids = new ArrayList<>();
        transaction.getProducts().forEach(product -> ids.add(product.getId()));
        return TransactionDto.builder()
                .id(transaction.getId())
                .number(transaction.getNumber())
                .status(transaction.getStatus() == null ? null : transaction.getStatus().name())
                .userId(transaction.getUser() == null ? null : transaction.getUser().getId())
                .prescriptionRequired(transaction.isPrescriptionRequired())
                .productIds(ids)
                .build();
    }
}