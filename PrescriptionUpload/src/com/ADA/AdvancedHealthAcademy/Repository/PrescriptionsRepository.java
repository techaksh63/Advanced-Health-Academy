package com.ADA.AdvancedHealthAcademy.Repository;

import com.ADA.AdvancedHealthAcademy.Entity.Prescriptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PrescriptionsRepository extends JpaRepository<Prescriptions, Long> {
//    @Query("SELECT p FROM Prescriptions p LEFT JOIN FETCH p.medicine m WHERE p.profileId = :profileId")
//    List<Prescriptions> findAllPrescriptionsWithMedicineByProfileId(@Param("profileId") long profileId);
    @Query("SELECT p.doctorName, p.doctorMobileNumber, p.patientName, p.patientMobileNumber, p.diagnosis, p.date, m.medicineName, m.medicinePower, m.frequency, m.dosage, m.duration, m.quantity FROM Prescriptions p LEFT JOIN p.medicine m WHERE p.profileId = :profileId")
    List<Object[]> findAllPrescriptionAndMedicineDataByProfileId(@Param("profileId") long profileId);

//    @Query(value = "Delete from prescriptions where profileId = :profileId", nativeQuery = true)
//    String deleteAllPrescriptionAndMedicineDataByProfileId(@Param("profileId") long profileId);

    @Query("SELECT p.doctorName, p.doctorMobileNumber, p.patientName, p.patientMobileNumber, p.diagnosis, p.date, m.medicineName, m.medicinePower, m.frequency, m.dosage, m.duration, m.quantity FROM Prescriptions p LEFT JOIN p.medicine m WHERE p.profileId = :profileId and p.prescriptionId =:prescriptionId")
    List<Object[]> findPrescriptionAndMedicineByProfileIdAndPrescriptionId(@Param("profileId") long profileId, @Param("prescriptionId") long prescriptionId);



}
