package com.ADA.AdvancedHealthAcademy.Converter.EntityToDTO;

import com.ADA.AdvancedHealthAcademy.DTO.PrescriptionInfoDTO;
import com.ADA.AdvancedHealthAcademy.Entity.Medicine;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Component
public class PrescriptionConverter {
   /* public PrescriptionInfoDTO convertToDTO(Object[] result) {
        PrescriptionInfoDTO prescriptionDTO = new PrescriptionInfoDTO();
        prescriptionDTO.setDoctorName((String) result[0]);
        prescriptionDTO.setDoctorMobileNumber((String) result[1]);
        prescriptionDTO.setPatientName((String) result[2]);
        prescriptionDTO.setPatientMobileNumber((String) result[3]);
        prescriptionDTO.setDiagnosis((String) result[4]);
        prescriptionDTO.setDate((LocalDate) result[5]);

        // If medicine data is available
        if (result.length > 6 && result[6] != null) {
            List<Medicine> medicines = new ArrayList<>();
            Medicine medicine = new Medicine();
            medicine.setMedicineName((String) result[6]);
            medicine.setMedicinePower((String) result[7]);
            medicine.setFrequency((String) result[8]);
            medicine.setDosage((String) result[9]);
            medicine.setDuration((String) result[10]);
            medicine.setQuantity((String) result[11]);
            medicines.add(medicine);
            prescriptionDTO.setMedicine(medicines);
        }

        return prescriptionDTO;
    }*/



        public List<PrescriptionInfoDTO> convert(List<Object> resultList) {
            List<PrescriptionInfoDTO> dtoList = new ArrayList<>();
            for (Object result : resultList) {
                Object[] objectArray = (Object[]) result; // Assuming each element is an Object array
                PrescriptionInfoDTO dto = new PrescriptionInfoDTO(
                        (String) objectArray[0], // doctorName
                        (String) objectArray[1], // doctorMobileNumber
                        (String) objectArray[2], // patientName
                        (String) objectArray[3], // patientMobileNumber
                        (String) objectArray[4], // diagnosis
                        (LocalDate) objectArray[5], // date (assuming LocalDate type)
                        getMedicineList(objectArray) // extract Medicine list
                );
                dtoList.add(dto);
            }
            return dtoList;
        }

        private static List<Medicine> getMedicineList(Object[] objectArray) {
            List<Medicine> medicineList = new ArrayList<>();
            // Assuming medicine data starts from index 6 (check your query structure)
            for (int i = 6; i < objectArray.length; i += 11) { // Assuming each medicine entry has 11 elements
                Medicine medicine = new Medicine(
                        (String) objectArray[i], // medicineName
                        (String) objectArray[i + 1], // medicinePower (might need type conversion)
                        (String) objectArray[i + 2], // frequency
                        (String) objectArray[i + 3], // dosage
                        (String) objectArray[i + 4], // duration (assuming Integer type)
                        (String) objectArray[i + 5]  // quantity (assuming Integer type)
                );
                medicineList.add(medicine);
            }
            return medicineList;
        }


}
