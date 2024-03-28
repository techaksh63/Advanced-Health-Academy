package com.ADA.AdvancedHealthAcademy.Service;

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

    public Prescriptions savePrescription(Prescriptions prescriptions) throws Exception {
        try {
            return repository.save(prescriptions);
        } catch (DataAccessException e) {
            throw new Exception("Error saving prescription: " + e.getMessage());
        }
    }
    public List<Prescriptions> getAllPrescriptions() throws Exception {
        try {
            return repository.findAll();
        } catch (DataAccessException e) {
            throw new Exception("Error retrieving all prescriptions: " + e.getMessage());
        }
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







