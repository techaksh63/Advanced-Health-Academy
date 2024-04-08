package com.ADA.AdvancedHealthAcademy.Service;

import com.ADA.AdvancedHealthAcademy.Converter.EntityToDTO.PrescriptionConverter;
import com.ADA.AdvancedHealthAcademy.DTO.PrescriptionInfoDTO;
import com.ADA.AdvancedHealthAcademy.Entity.Prescriptions;
import com.ADA.AdvancedHealthAcademy.Repository.PrescriptionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionsService {
    @Autowired
    private PrescriptionsRepository repository;
    @Autowired
    private PrescriptionConverter prescriptionConverter;

    public Prescriptions savePrescription(Prescriptions prescriptions) throws Exception {
        try {
            return repository.save(prescriptions);
        } catch (DataAccessException e) {
            throw new Exception("Error saving prescription: " + e.getMessage());
        }
    }


    public List<PrescriptionInfoDTO> findAllPrescriptionAndMedicineDataByProfileId(long profileId) throws Exception {
        try {
        List<Object[]> results = repository.findAllPrescriptionAndMedicineDataByProfileId(profileId);
        List<PrescriptionInfoDTO> prescriptionInfoDTOS = prescriptionConverter.convertQueryToPrescriptionInfoDTO(results);
        return prescriptionInfoDTOS;
        } catch (DataAccessException e) {
            throw new Exception("Error retrieving all prescriptions by profileId: " + e.getMessage());
        }
    }


    public Optional<PrescriptionInfoDTO> findPrescriptionById(Long profileId,Long prescriptionId) throws Exception {
        try {
            List<Object[]> results = repository.findPrescriptionAndMedicineByProfileIdAndPrescriptionId(profileId,prescriptionId);
            Optional<PrescriptionInfoDTO> prescriptionInfoDTO = prescriptionConverter.convertPrescriptionAndMedicine(results);
            return prescriptionInfoDTO;
        } catch (DataAccessException e) {
            throw new Exception("Error finding prescription by ID: " + e.getMessage());
        }
    }

    public void deletePrescriptionById(Long prescriptionId) throws Exception {
        try {
            repository.deleteById(prescriptionId);
        } catch (DataAccessException e) {
            throw new Exception("Error deleting prescription: " + e.getMessage());
        }
    }

}







