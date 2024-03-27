package com.ADA.AdvancedHealthAcademy.Service;

import com.ADA.AdvancedHealthAcademy.Entity.Medicine;
import com.ADA.AdvancedHealthAcademy.Entity.Prescriptions;
import com.ADA.AdvancedHealthAcademy.Entity.PrescriptionsUpload;
import com.ADA.AdvancedHealthAcademy.Repository.PrescriptionsRepository;
import com.ADA.AdvancedHealthAcademy.Repository.PrescriptionsUploadRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;


@Service
public class PrescriptionsUploadService {
    @Autowired
    private PrescriptionsUploadRepository prescriptionsUploadRepository;
    @Autowired
    private PrescriptionsRepository prescriptionsRepository;

    public PrescriptionsUploadService(PrescriptionsRepository prescriptionsRepository) {
        this.prescriptionsRepository = prescriptionsRepository;
    }
    public PrescriptionsUpload saveImage(PrescriptionsUpload prescription){
        return prescriptionsUploadRepository.save(prescription);
    }

    public Prescriptions addPrescription(MultipartFile file) throws Exception {
        String text = OCR(file);

        JSONObject JsonOutput = convertTextToJson(text);
        System.out.println(JsonOutput);


        Prescriptions savedPrescription = savePrescriptionFromJson(String.valueOf(JsonOutput));
        System.out.println(savedPrescription);
        return savedPrescription;
    }

     private String OCR(MultipartFile file)throws IOException, TesseractException{
        String text = null;
        Tesseract tesseract = new Tesseract( ) ;
        try {
            tesseract.setDatapath( "Tess4J/tessdata" ) ;
            BufferedImage image = ImageIO.read(file.getInputStream());
            text = tesseract.doOCR(image) ;

            return text;
        }
        catch ( TesseractException e ) {
            e.printStackTrace( ) ;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "Error";
    }



    private JSONObject convertTextToJson(String text) throws JSONException {
        org.json.JSONObject jsonData = new org.json.JSONObject();
        String[] lines = text.split("\n");
        List<org.json.JSONObject> medicineList = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.trim().split("=", 2);

            if (parts.length == 2) {
                String key = parts[0].trim();
                String value = parts[1].trim();

                if (key.startsWith("Medicine")) {
                    String[] medicineDetails = value.split(":");
                    if (medicineDetails.length == 4) {
                        org.json.JSONObject medicineJson = new JSONObject();
                        medicineJson.put("medicineName", medicineDetails[0].trim());
                        medicineJson.put("medicinePower", medicineDetails[1].trim());
                        medicineJson.put("dosage", Integer.parseInt(medicineDetails[2].trim()));
                        medicineJson.put("frequency", Integer.parseInt(medicineDetails[3].trim()));
                        medicineList.add(medicineJson);
                    }
                } else {
                    jsonData.put(key, value);
                }
            }
        }
        jsonData.put("medicine", new JSONArray(medicineList));
        return jsonData;
    }


    private Prescriptions savePrescriptionFromJson(String jsonData) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JSONObject jsonObject = new JSONObject(jsonData);

        Prescriptions prescription = new Prescriptions();
        prescription.setDoctorName(jsonObject.getString("Doctor"));
        prescription.setInstruction(jsonObject.getString("Instruction"));

        JSONArray medicineArray = jsonObject.getJSONArray("medicine");
        List<Medicine> medicines = new ArrayList<>();
        for (int i = 0; i < medicineArray.length(); i++) {
            JSONObject medicineJson = medicineArray.getJSONObject(i);
            Medicine medicine = new Medicine(
                    medicineJson.getString("medicineName"),
                    medicineJson.getString("medicinePower"),
                    medicineJson.getInt("dosage"),
                    medicineJson.getInt("frequency")
            );
            medicines.add(medicine);
        }
        prescription.setMedicine(medicines);

        Prescriptions savedPrescription = prescriptionsRepository.save(prescription);
        return savedPrescription;
    }

}
