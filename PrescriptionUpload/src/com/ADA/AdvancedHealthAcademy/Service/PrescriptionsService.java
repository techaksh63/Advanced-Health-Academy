package com.ADA.AdvancedHealthAcademy.Service;

import com.ADA.AdvancedHealthAcademy.Converter.EntityToDTO.PrescriptionConverter;
import com.ADA.AdvancedHealthAcademy.DTO.PrescriptionInfoDTO;
import com.ADA.AdvancedHealthAcademy.Entity.Prescriptions;
import com.ADA.AdvancedHealthAcademy.Exceptions.ResourceNotFoundException;
import com.ADA.AdvancedHealthAcademy.Repository.PrescriptionsRepository;
import com.ADA.AdvancedHealthAcademy.Repository.PrescriptionsUploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionsService {
    @Autowired
    private PrescriptionsRepository prescriptionsRepository;
    @Autowired
    private PrescriptionConverter prescriptionConverter;
    @Autowired
    private PrescriptionsUploadRepository prescriptionsUploadRepository;

    public List<PrescriptionInfoDTO> findAllPrescriptionAndMedicineDataByProfileId(long profileId) throws Exception {
        try {
            Long count = prescriptionsUploadRepository.profileCountById(profileId);
            if(count==1){
                boolean is_active = prescriptionsUploadRepository.isActiveProfile(profileId);
                if(is_active){
                    List<Object[]> results = prescriptionsRepository.findAllPrescriptionAndMedicineDataByProfileId(profileId);
                    List<PrescriptionInfoDTO> prescriptionInfoDTOS = prescriptionConverter.convertQueryToPrescriptionInfoDTO(results);
                    return prescriptionInfoDTOS;
                }else {
                    throw new Exception("Profile is Inactive with ID: "+ profileId);
                }
            }else {
                throw new Exception("Profile not found with ID: "+ profileId);
            }
        } catch (DataAccessException e) {
            throw new Exception("Error retrieving all prescriptions by ProfileId: " + e.getMessage());
        }
    }


    public Optional<PrescriptionInfoDTO> findPrescriptionById(Long profileId,Long prescriptionId) throws Exception {
        try {
            Long count = prescriptionsUploadRepository.profileCountById(profileId);
            if(count==1){
                boolean is_active = prescriptionsUploadRepository.isActiveProfile(profileId);
                if(is_active){
                    List<Object[]> results = prescriptionsRepository.findPrescriptionAndMedicineByProfileIdAndPrescriptionId(profileId,prescriptionId);
                    Optional<PrescriptionInfoDTO> prescriptionInfoDTO = prescriptionConverter.convertPrescriptionAndMedicine(results);
                    return prescriptionInfoDTO;
                }else {
                    throw new Exception("Profile is Inactive with ID: "+ profileId);
                }
            }else {
                throw new Exception("Profile not found with ID: "+ profileId);
            }
        } catch (DataAccessException e) {
            throw new Exception("Error finding prescription by ID: " + e.getMessage());
        }
    }

    public void deletePrescriptionById(Long profileId,Long prescriptionId) throws Exception {
        try {
            Long count = prescriptionsUploadRepository.profileCountById(profileId);
            if(count==1){
                boolean is_active = prescriptionsUploadRepository.isActiveProfile(profileId);
                if(is_active){
                    prescriptionsRepository.deleteById(prescriptionId);
                }else {
                    throw new Exception("Profile is Inactive with ID: "+ profileId);
                }
            }else {
                throw new Exception("Profile not found with ID: "+ profileId);
            }
        } catch (DataAccessException e) {
            throw new Exception("Error deleting prescription: " + e.getMessage());
        }
    }

    public String deleteAllPrescriptionByIdProfileId(Long profileId) throws Exception {
        try {
            Long count = prescriptionsUploadRepository.profileCountById(profileId);
            if(count==1){
                boolean is_active = prescriptionsUploadRepository.isActiveProfile(profileId);
                if(is_active){

                    List<Prescriptions> prescriptionOptional = prescriptionsRepository.findAllPrescriptionByProfileId(profileId);
                    if (!prescriptionOptional.isEmpty()) {
                        for (int k=0;k<=prescriptionOptional.size()-1;k++){
                            prescriptionsRepository.deleteById(prescriptionOptional.get(k).getPrescriptionId()); ;
                        }
                        return "Successfully Deleted All prescriptions";
                    }else {
                        throw new ResourceNotFoundException("Prescription Does not exist with profile ID: " + profileId);
                    }
                }else {
                    throw new Exception("Profile is Inactive with ID: "+ profileId);
                }
            }else {
                throw new Exception("Profile not found with ID: "+ profileId);
            }
        } catch (DataAccessException e) {
            throw new Exception("Error deleting All prescriptions by profile Id: "+ profileId +" "+ e.getMessage());
        }
    }

}







