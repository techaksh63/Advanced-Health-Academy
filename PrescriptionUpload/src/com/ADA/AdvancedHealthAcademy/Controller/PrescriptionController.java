package com.ADA.AdvancedHealthAcademy.Controller;

import com.ADA.AdvancedHealthAcademy.DTO.PrescriptionInfoDTO;
import com.ADA.AdvancedHealthAcademy.Exceptions.ResourceNotFoundException;
import com.ADA.AdvancedHealthAcademy.Service.PrescriptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PrescriptionController {
    @Autowired
    private PrescriptionsService prescriptionsService;


    @GetMapping("/{profileId}/all-prescriptions")
    public ResponseEntity<?> getAllPrescriptionsByProfileId(@PathVariable Long profileId) throws Exception{
        try {
            List<PrescriptionInfoDTO> prescriptions = prescriptionsService.findAllPrescriptionAndMedicineDataByProfileId(profileId);
            if (!prescriptions.isEmpty()){
                return ResponseEntity.ok(prescriptions);
            }else {
                throw new ResourceNotFoundException("Prescription not found with Profile ID: " + profileId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @GetMapping("/{profileId}/prescription/{prescriptionId}")
    public ResponseEntity<?> findPrescriptionById(@PathVariable Long profileId,@PathVariable Long prescriptionId) throws Exception{
        try {
            Optional<PrescriptionInfoDTO> prescriptionOptional = prescriptionsService.findPrescriptionById(profileId,prescriptionId);
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

    @DeleteMapping("/{profileId}/prescription/{prescriptionId}/delete")
    public ResponseEntity<?> deletePrescriptionById(@PathVariable Long profileId,@PathVariable Long prescriptionId) {
        try {
            Optional<PrescriptionInfoDTO> prescriptionOptional = prescriptionsService.findPrescriptionById(profileId,prescriptionId);
            if (prescriptionOptional.isPresent()) {
                prescriptionsService.deletePrescriptionById(profileId,prescriptionId);
                return ResponseEntity.ok("Successfully Deleted");
            } else {
                throw new ResourceNotFoundException("Prescription Does not exist with ID: " + prescriptionId);
            }
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @DeleteMapping("/{profileId}/prescription/delete")
    public ResponseEntity<?> deleteAllPrescriptionByProfileId(@PathVariable Long profileId) {
        try {
            List<PrescriptionInfoDTO> prescriptionOptional = prescriptionsService.findAllPrescriptionAndMedicineDataByProfileId(profileId);
            if (!prescriptionOptional.isEmpty()) {
               String result = prescriptionsService.deleteAllPrescriptionByIdProfileId(profileId);
                return ResponseEntity.ok(result);
            } else {
                throw new ResourceNotFoundException("Prescription Does not exist with profile ID: " + profileId);
            }
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}







