package com.ADA.AdvancedHealthAcademy.Controller;

import com.ADA.AdvancedHealthAcademy.Entity.Prescriptions;
import com.ADA.AdvancedHealthAcademy.Exceptions.ResourceNotFoundException;
import com.ADA.AdvancedHealthAcademy.Service.PrescriptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/prescription")
public class PrescriptionController {
    @Autowired
    private PrescriptionsService prescriptionsService;

    @GetMapping
    public ResponseEntity<?> getAllPrescriptions() {
        try {
            List<Prescriptions> prescriptions = prescriptionsService.getAllPrescriptions();
            return ResponseEntity.ok(prescriptions);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{prescriptionId}")
    public ResponseEntity<?> findPrescriptionById(@PathVariable Long prescriptionId) {
        try {
            Optional<Prescriptions> prescriptionOptional = prescriptionsService.findPrescriptionById(prescriptionId);
            if (prescriptionOptional.isPresent()) {
                return ResponseEntity.ok(prescriptionOptional.get());
            } else {
                throw new ResourceNotFoundException("Prescription not found with ID: " + prescriptionId);
            }
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{prescriptionId}")
    public ResponseEntity<?> deletePrescriptionById(@PathVariable Long prescriptionId) {
        try {
            Optional<Prescriptions> prescriptionOptional = prescriptionsService.findPrescriptionById(prescriptionId);
            if (prescriptionOptional.isPresent()) {
                prescriptionsService.deletePrescriptionById(prescriptionId);
                return ResponseEntity.ok("Successfully Deleted");
            } else {
                throw new ResourceNotFoundException("To Delete, Prescription not found with ID: " + prescriptionId);
            }
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}







