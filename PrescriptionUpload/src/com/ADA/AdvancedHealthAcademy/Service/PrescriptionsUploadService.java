package com.ADA.AdvancedHealthAcademy.Service;

import com.ADA.AdvancedHealthAcademy.Entity.Medicine;
import com.ADA.AdvancedHealthAcademy.Entity.Prescriptions;
import com.ADA.AdvancedHealthAcademy.Entity.PrescriptionsUpload;
import com.ADA.AdvancedHealthAcademy.Repository.PrescriptionsRepository;
import com.ADA.AdvancedHealthAcademy.Repository.PrescriptionsUploadRepository;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;


@Service
public class PrescriptionsUploadService {
    @Autowired
    private PrescriptionsUploadRepository prescriptionsUploadRepository;
    @Autowired
    private final PrescriptionsRepository prescriptionsRepository;

    public PrescriptionsUploadService(PrescriptionsRepository prescriptionsRepository) {
        this.prescriptionsRepository = prescriptionsRepository;
    }
    public PrescriptionsUpload saveImage(PrescriptionsUpload prescription)throws Exception, FileNotFoundException {
        return prescriptionsUploadRepository.save(prescription);
    }

    public Prescriptions addPrescription(long profileId,MultipartFile file) throws Exception {
        String text;
        try {
            text = OCR(file);
        } catch (TesseractException | RuntimeException e) {
            throw new Exception("Error during OCR processing: " + e.getMessage());
        }

        JSONObject JsonOutput;
        try {
            JsonOutput = convertTextToJson(text);
        } catch (JSONException e) {
            throw new Exception("Error converting text to JSON: " + e.getMessage());
        }

        Prescriptions savedPrescription = savePrescriptionFromJson(profileId,String.valueOf(JsonOutput));
        return savedPrescription;
    }


     private String OCR(MultipartFile file)throws TesseractException{
        String text = null;
        Tesseract tesseract = new Tesseract( ) ;
        try {
            tesseract.setDatapath( "Tess4J/tessdata" ) ;
            BufferedImage image = ImageIO.read(file.getInputStream());
            text = tesseract.doOCR(image);

            return text;
        }
        catch ( TesseractException e ) {
            throw e ;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




    private JSONObject convertTextToJson(String text) throws JSONException {
        JSONObject jsonData = new JSONObject();

        String[] lines = text.split("\n");
        for (String line : lines) {
            String[] parts = line.trim().split(":", 2);
            if (parts.length == 2) {
                String key = parts[0].trim();
                String value = parts[1].trim();

                if (key.equals("Doctor")) {
                    String[] doctorInfo = value.split(" \\(");
                    jsonData.put("Doctor Name", doctorInfo[0]);
                    jsonData.put("Doctor Mo.", doctorInfo[1].replace(")", ""));
                } else {
                    jsonData.put(key, value);
                }
            }
        }

        boolean isMedicineSection = false;
        JSONArray medicineArray = new JSONArray();
        for (String line : lines) {
            if (line.isBlank()) {
                isMedicineSection = true;
                continue;
            }

            if (isMedicineSection) {
                String[] medicineDetails = line.trim().split("\\|");
                if (medicineDetails.length == 6) {
                    JSONObject medicineJson = getJsonObject(medicineDetails);
                    medicineArray.put(medicineJson);
                }
            }
        }

        jsonData.put("Medicine", medicineArray);
        return jsonData;
    }
    private static JSONObject getJsonObject(String[] medicineDetails) throws JSONException {
        JSONObject medicineJson = new JSONObject();
        medicineJson.put("Medicine Name", medicineDetails[0].trim());
        medicineJson.put("Medicine Power", medicineDetails[1].trim());
        medicineJson.put("Frequency (MN-AF-EN-NT)", medicineDetails[2].trim());
        medicineJson.put("Consume (Af or Bf)", medicineDetails[3].trim());
        medicineJson.put("Duration", medicineDetails[4].trim());
        medicineJson.put("Quantity", medicineDetails[5].trim());
        return medicineJson;
    }



    private Prescriptions savePrescriptionFromJson(long profileId,String jsonData) throws Exception {
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(jsonData);
        } catch (JSONException e) {
            throw new Exception("Error parsing JSON data: " + e.getMessage());
        }
        Prescriptions prescription = new Prescriptions();

        prescription.setDoctorName(jsonObject.getString("Doctor Name"));
        prescription.setDoctorMobileNumber(jsonObject.getString("Doctor Mo."));

        prescription.setPatientName(jsonObject.getString("Patient"));
        prescription.setPatientMobileNumber(jsonObject.getString("Patient Mo."));
        prescription.setDiagnosis(jsonObject.getString("Diagnosis"));
        prescription.setDate(LocalDate.parse(jsonObject.getString("Date")));

        JSONArray medicineArray = jsonObject.getJSONArray("Medicine");
        List<Medicine> medicines = new ArrayList<>();
        for (int i = 0; i < medicineArray.length(); i++) {
            JSONObject medicineJson = medicineArray.getJSONObject(i);
            Medicine medicine = new Medicine(
                    medicineJson.getString("Medicine Name"),
                    medicineJson.getString("Medicine Power"),
                    medicineJson.getString("Frequency (MN-AF-EN-NT)"),
                    medicineJson.getString("Consume (Af or Bf)"),
                    medicineJson.getString("Duration"),
                    medicineJson.getString("Quantity")
            );
            medicines.add(medicine);
        }
        prescription.setMedicine(medicines);
        prescription.setProfileId(profileId);
        Prescriptions savedPrescription;
        try {
            savedPrescription = prescriptionsRepository.save(prescription);
        } catch (Exception e) {
            throw new Exception("Error saving prescription to database: " + e.getMessage());
        }
        return savedPrescription;
    }

}