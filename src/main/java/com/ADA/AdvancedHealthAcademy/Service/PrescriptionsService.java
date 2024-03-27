package com.ADA.AdvancedHealthAcademy.Service;

import com.ADA.AdvancedHealthAcademy.Entity.Prescriptions;
import com.ADA.AdvancedHealthAcademy.Repository.PrescriptionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionsService {
    @Autowired
    private PrescriptionsRepository repository;

    public Prescriptions savePrescription(Prescriptions prescriptions){
        return repository.save(prescriptions);
    }

    public List<Prescriptions> AllPrescription(){
        return repository.findAll();
    }

    public Optional<Prescriptions> findPrescriptionById(Long prescriptionId){
        return repository.findById(prescriptionId);
    }

    public void deletePrescriptionById(Long prescriptionId){
        repository.deleteById(prescriptionId);
    }

}
