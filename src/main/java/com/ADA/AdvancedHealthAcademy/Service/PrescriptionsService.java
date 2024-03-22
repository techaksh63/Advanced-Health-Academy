package com.ADA.AdvancedHealthAcademy.Service;

import com.ADA.AdvancedHealthAcademy.Entity.Prescriptions;
import com.ADA.AdvancedHealthAcademy.Repository.PrescriptionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionsService {
    @Autowired
    private PrescriptionsRepository repository;

    public Prescriptions savePrescription(Prescriptions prescriptions){
        return repository.save(prescriptions);
    }

}
