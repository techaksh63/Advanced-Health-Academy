package com.ADA.AdvancedHealthAcademy.Controller;

import com.ADA.AdvancedHealthAcademy.Entity.PrescriptionsUpload;
import com.ADA.AdvancedHealthAcademy.Service.PrescriptionsUploadService;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionsUploadController {
    @Autowired
    private PrescriptionsUploadService service;

    @PostMapping
    public String addPrescription(@RequestParam("image") MultipartFile file) throws IOException, SerialException, SQLException, TesseractException {
        byte[] bytes = file.getBytes();
        Blob blob = new SerialBlob(bytes);
        PrescriptionsUpload image = new PrescriptionsUpload();
        image.setImage(blob);

        service.saveImage(image);

        return service.OCR(file);
    }
}
