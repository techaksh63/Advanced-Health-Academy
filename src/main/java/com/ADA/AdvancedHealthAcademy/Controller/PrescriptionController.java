package com.ADA.AdvancedHealthAcademy.Controller;

import com.ADA.AdvancedHealthAcademy.Entity.Prescriptions;
import com.ADA.AdvancedHealthAcademy.Service.PrescriptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/prescription")
public class PrescriptionController {
    @Autowired
    private PrescriptionsService service;

    @PostMapping
    public ResponseEntity<Prescriptions> addPrescription(@RequestBody Prescriptions prescriptions) {
        service.savePrescription(prescriptions);
        return new ResponseEntity<Prescriptions>(service.savePrescription(prescriptions), HttpStatus.CREATED);
    }
}
