package com.ADA.AdvancedHealthAcademy.Controller;

import com.ADA.AdvancedHealthAcademy.Entity.Prescriptions;
import com.ADA.AdvancedHealthAcademy.Entity.PrescriptionsUpload;
import com.ADA.AdvancedHealthAcademy.Service.PrescriptionsService;
import com.ADA.AdvancedHealthAcademy.Service.PrescriptionsUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;

@RestController
@RequestMapping("/api")
public class PrescriptionsUploadController {
    @Autowired
    private PrescriptionsUploadService prescriptionsUploadService;
    @Autowired
    private PrescriptionsService prescriptionsService;

    @PostMapping("/{profileId}/upload-prescription")
    public ResponseEntity<?> uploadPrescription(@PathVariable Long profileId,@RequestParam("image") MultipartFile file) {
        try {
        byte[] bytes = file.getBytes();
        Blob blob = new SerialBlob(bytes);
        PrescriptionsUpload image = new PrescriptionsUpload();
        image.setImage(blob);
        prescriptionsUploadService.saveImage(profileId,image);
        Prescriptions savedPrescription = prescriptionsUploadService.addPrescription(profileId,file);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPrescription);
        }catch (FileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error reading image file: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading prescription Details: " + e.getMessage());
        }
    }

}
