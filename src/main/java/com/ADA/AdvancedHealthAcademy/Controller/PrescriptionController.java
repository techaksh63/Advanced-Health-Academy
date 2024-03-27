package com.ADA.AdvancedHealthAcademy.Controller;

import com.ADA.AdvancedHealthAcademy.Entity.Prescriptions;
import com.ADA.AdvancedHealthAcademy.Service.PrescriptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescription")
public class PrescriptionController {
    @Autowired
    private PrescriptionsService prescriptionsService;

    @GetMapping
    public ResponseEntity<List<Prescriptions>> getAllPrescription(){
        List<Prescriptions> prescriptions = prescriptionsService.AllPrescription();
        return ResponseEntity.ok(prescriptions);
    }

    @GetMapping("/{prescriptionId}")
    private ResponseEntity<Prescriptions> findPrescriptionById(@PathVariable Long prescriptionId){
        return prescriptionsService.findPrescriptionById(prescriptionId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{prescriptionId}")
    private ResponseEntity<Void> deletePrescriptionById(@PathVariable Long prescriptionId){
        prescriptionsService.deletePrescriptionById(prescriptionId);
        return ResponseEntity.noContent().build();
    }
}
