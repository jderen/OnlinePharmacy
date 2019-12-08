package com.app.pharmacy.model.dto;

import com.app.pharmacy.model.Prescription;
import com.app.pharmacy.model.Transaction;
import com.app.pharmacy.model.User;
import com.app.pharmacy.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrescriptionDto {
    private Long id;
    private String photo;
    private Long userId;
    private Long transactionId;


    public static Prescription getPrescriptionByPrescriptionDto(PrescriptionDto prescriptionDto){
        return Prescription.builder()
                .id(prescriptionDto.id)
                .photo(prescriptionDto.photo)
                .user(new User(prescriptionDto.userId))
                .transaction(new Transaction(prescriptionDto.transactionId))
                .build();
    }

    public static PrescriptionDto getPrescriptionDtoByPrescription(Prescription prescription){
        return PrescriptionDto.builder()
                .id(prescription.getId())
                .photo(prescription.getPhoto())
                .userId(prescription.getUser() == null ? null : prescription.getUser().getId())
                .transactionId(prescription.getTransaction() == null ? null : prescription.getTransaction().getId())
                .build();
    }
}