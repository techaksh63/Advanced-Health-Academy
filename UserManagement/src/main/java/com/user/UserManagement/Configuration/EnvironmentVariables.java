package com.user.UserManagement.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentVariables {
    @Value("${get.all.prescription.info.api.url}")
    private String getAllPrescriptionInfoById;
    @Value("${get.prescription.info.api.url}")
    private String getPrescriptionInfoById;

    @Value("${delete.prescription.info.api.url}")
    private String deletePrescriptionById;

    @Value("${delete.all.prescription.info.api.url}")
    private String deleteAllPrescriptionById;

    public String getAllPrescriptionInfoById() {
        return getAllPrescriptionInfoById;
    }

    public String getPrescriptionInfoById() {
        return getPrescriptionInfoById;
    }

    public String deletePrescriptionById() {
        return deletePrescriptionById;
    }

    public String deleteAllPrescriptionById() {
        return deleteAllPrescriptionById;
    }
}
