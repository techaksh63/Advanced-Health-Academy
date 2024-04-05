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
import java.util.stream.Collectors;

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
//    public List<Prescriptions> getAllPrescriptions() throws Exception {
//        try {
//            List<Object[]> results = repository.findPrescriptionAndMedicineDataByProfileId(profileId);
//            return prescriptionConverter.convertToDTOList(results);
//            return repository.findAllPrescriptionsWithMedicineByProfileId(2);
//
//        } catch (DataAccessException e) {
//            throw new Exception("Error retrieving all prescriptions: " + e.getMessage());
//        }
//    }

    public List<PrescriptionInfoDTO> findPrescriptionAndMedicineDataByProfileId() {
        List<Object> results = repository.findPrescriptionAndMedicineDataByProfileId(1);
        List<PrescriptionInfoDTO> prescriptionInfoDTOS = prescriptionConverter.convert(results);
        return prescriptionInfoDTOS;
    }


    public Optional<Prescriptions> findPrescriptionById(Long prescriptionId) throws Exception {
        try {
            return repository.findById(prescriptionId);
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







