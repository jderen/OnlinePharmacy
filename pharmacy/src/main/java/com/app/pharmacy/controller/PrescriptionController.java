package com.app.pharmacy.controller;

import com.app.pharmacy.model.dao.PrescriptionDao;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/prescription")
public class PrescriptionController {

    private final PrescriptionDao prescriptionDao;

    @Autowired
    public PrescriptionController(PrescriptionDao prescriptionDao) {
        this.prescriptionDao = prescriptionDao;
    }

    @RequestMapping(value = "/{id}/photo", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getPhoto(@PathVariable Long id) throws IOException {

        var imgFile = new ClassPathResource(prescriptionDao.getPhotoPathByPrescriptionId(id));
        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }
}
