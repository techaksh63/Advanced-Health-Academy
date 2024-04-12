package com.ADA.AdvancedHealthAcademy.Converter.EntityToDTO;

import com.ADA.AdvancedHealthAcademy.DTO.MedicineInfoDTO;
import com.ADA.AdvancedHealthAcademy.DTO.PrescriptionInfoDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
public class PrescriptionConverter {

    public static List<PrescriptionInfoDTO> convertQueryToPrescriptionInfoDTO(List<Object[]> data) {
        Map<String, PrescriptionInfoDTO> prescriptionsMap = new HashMap<>();

        for (Object[] row : data) {
            String key = createPrescriptionKey(row);
            PrescriptionInfoDTO dto = prescriptionsMap.get(key);

            if (dto == null) {
                dto = new PrescriptionInfoDTO();
                dto.setDoctorName((String) row[0]);
                dto.setDoctorMobileNumber((String) row[1]);
                dto.setPatientName((String) row[2]);
                dto.setPatientMobileNumber((String) row[3]);
                dto.setDiagnosis((String) row[4]);
                dto.setDate((LocalDate) row[5]);

                dto.setMedicine(new ArrayList<>());
                prescriptionsMap.put(key, dto);
            }

            String medicineName = (String) row[6];
            String medicinePower = (String) row[7];
            String frequency = (String) row[8];
            String dosage = (String) row[9];
            String duration = (String) row[10];
            String quantity = (String) row[11];

            MedicineInfoDTO medicine = new MedicineInfoDTO(medicineName, medicinePower, frequency, dosage, duration, quantity);
            dto.getMedicine().add(medicine);
        }

        return new ArrayList<>(prescriptionsMap.values());
    }
    private static String createPrescriptionKey(Object[] row) {
        StringBuilder keyBuilder = new StringBuilder();
        keyBuilder.append((String) row[0]).append("-").append((String) row[2]).append("-").append((String) row[4]).append("-").append(row[5].toString());
        return keyBuilder.toString();
    }




    public static Optional<PrescriptionInfoDTO> convertPrescriptionAndMedicine(List<Object[]> resultList) {
        if (resultList.isEmpty()) {
            return Optional.empty();
        }

        Object[] firstRow = resultList.get(0);
        PrescriptionInfoDTO dto = new PrescriptionInfoDTO(
                (String) firstRow[0],
                (String) firstRow[1],
                (String) firstRow[2],
                (String) firstRow[3],
                (String) firstRow[4],
                (LocalDate) firstRow[5],
                new ArrayList<>()
        );

        for (Object[] row : resultList) {
            MedicineInfoDTO medicineDTO = new MedicineInfoDTO(
                    (String) row[6],
                    (String) row[7],
                    (String) row[8],
                    (String) row[9],
                    (String) row[10],
                    (String) row[11]
            );
            dto.getMedicine().add(medicineDTO);
        }

        return Optional.of(dto);
    }






}
