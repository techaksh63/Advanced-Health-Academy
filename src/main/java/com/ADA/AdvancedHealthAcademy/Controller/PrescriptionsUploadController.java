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
import java.sql.Blob;

@RestController
@RequestMapping("/api/upload-prescription")
public class PrescriptionsUploadController {
    @Autowired
    private PrescriptionsUploadService prescriptionsUploadService;
    @Autowired
    private PrescriptionsService prescriptionsService;

    @PostMapping
    public  ResponseEntity<Prescriptions> addPrescription(@RequestParam("image") MultipartFile file) throws Exception {
        byte[] bytes = file.getBytes();
        Blob blob = new SerialBlob(bytes);
        PrescriptionsUpload image = new PrescriptionsUpload();
        image.setImage(blob);
        prescriptionsUploadService.saveImage(image);

        return new ResponseEntity<Prescriptions>(prescriptionsUploadService.addPrescription(file), HttpStatus.CREATED);
    }


}
