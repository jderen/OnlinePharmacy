package com.app.pharmacy.controller;

import com.app.pharmacy.model.dao.PrescriptionDao;
import com.app.pharmacy.service.StorageService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@RestController
@RequestMapping("/api/prescription")
public class PrescriptionController {

    private final PrescriptionDao prescriptionDao;
  //  private final StorageService storageService;

    @Autowired
    public PrescriptionController(PrescriptionDao prescriptionDao/*, StorageService storageService*/) {
        this.prescriptionDao = prescriptionDao;
        //this.storageService = storageService;
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

//    @GetMapping("/allFiles")
//    public String listUploadedFiles(Model model) throws IOException {
//
//        model.addAttribute("files", storageService.loadAll().map(
//                path -> MvcUriComponentsBuilder.fromMethodName(PrescriptionController.class,
//                        "serveFile", path.getFileName().toString()).build().toString())
//                .collect(Collectors.toList()));
//
//        return "uploadForm";
//    }
//
//    @GetMapping("/files/{filename:.+}")
//    @ResponseBody
//    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
//
//        Resource file = storageService.loadAsResource(filename);
//        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
//                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
//    }
//
//    @PostMapping("/")
//    public String handleFileUpload(@RequestParam("file") MultipartFile file,
//                                   RedirectAttributes redirectAttributes) {
//
//        storageService.store(file);
//        redirectAttributes.addFlashAttribute("message",
//                "You successfully uploaded " + file.getOriginalFilename() + "!");
//
//        return "redirect:/";
//    }
}
