package com.ADA.AdvancedHealthAcademy.Service;

import com.ADA.AdvancedHealthAcademy.Entity.Medicine;
import com.ADA.AdvancedHealthAcademy.Entity.Prescriptions;
import com.ADA.AdvancedHealthAcademy.Entity.PrescriptionsUpload;
import com.ADA.AdvancedHealthAcademy.Repository.PrescriptionsRepository;
import com.ADA.AdvancedHealthAcademy.Repository.PrescriptionsUploadRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PrescriptionsUploadService {
    @Autowired
    private PrescriptionsUploadRepository repository;
    @Autowired
    private PrescriptionsRepository repository2;

    public PrescriptionsUpload saveImage(PrescriptionsUpload prescription){
        return repository.save(prescription);
    }


    public String OCR(MultipartFile file)throws IOException, TesseractException{
        String output = null;
        Tesseract tesseract = new Tesseract( ) ;
        try {

            tesseract.setDatapath( "Tess4J/tessdata" ) ;
            BufferedImage image = ImageIO.read(file.getInputStream());
            output = tesseract.doOCR(image) ;
//            return output;



            Pattern pattern = Pattern.compile("(.*?)\\s=(.*)");


            Matcher matcher = pattern.matcher(output);

            // Create a HashMap to store key-value pairs
//            HashMap<String, Object> data = new HashMap<String, Object>();

            // Create a JSONObject to store data
            JSONObject data = new JSONObject();

            while (matcher.find()) {
                // Extract the key and value from the match
                String key = matcher.group(1).trim();
                String value = matcher.group(2).trim();

                // Add the key-value pair to the HashMap
                data.put(key, value);
            }

            // Print the extracted data
//            System.out.println(data);



            Prescriptions newPrescriptions = new Prescriptions();

            Object doctorName = data.get("Doctor");
            if (doctorName != null) {
                newPrescriptions.setDoctorName((String) doctorName);
                System.out.println("Doctor Name: " + doctorName);
            } else {
                System.out.println("Doctor key not found in the data.");
            }



//             List<Medicine> medicine = new ArrayList<>();
//            Object Medicines = data.get("Medicine name");
//            Object Power = data.get("Medicine power");
//            Object Dosage = data.get("Dosage");
//            Object Frequency = data.get("Frequency");
//            if(Medicines != null){
//
//                medicine.add(new Medicine((String) Medicines, (String) Power, (Integer) Dosage,(Integer) Frequency));
////                medicine.setMedicineName(String.valueOf(medicine));
//                newPrescriptions.setMedicine(medicine);
//            }else {
//                System.out.println("Medicine key not found in the data.");
//            }

            System.out.println(newPrescriptions);
//            repository2.save(newPrescriptions);


            return output;



        }
        catch ( TesseractException e ) {
            e.printStackTrace( ) ;
        }
        return "Error";
    }



//    public Prescriptions processJsonData(String jsonData) throws Exception {
//        ObjectMapper mapper = new ObjectMapper(); // Create ObjectMapper for JSON parsing
//
//        // Parse JSON string into a HashMap
//        HashMap<String, Object> dataMap = mapper.readValue(jsonData, HashMap.class);
//
//        // Create a new Prescriptions object
//        Prescriptions prescription = new Prescriptions();
//
//        // Loop through the data map
//        for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
//            String key = entry.getKey();
//            Object value = entry.getValue();
//
//            // Check if the key matches an entity field
//            if (key.equals("doctorName")) {
//                prescription.setDoctorName((String) value);
//            } else if (key.equals("medicine")) {
//                // Handle Medicine details (assuming it's a list of objects)
//                // You might need to modify this logic based on your actual JSON structure
//                java.util.List<Medicine> medicines = parseMedicineList((List<Object>) value);
//                prescription.setMedicine(medicines);
//            } else {
//                // Add unmatched data to information map
//                if (prescription.getInformation() == null) {
//                    prescription.setInformation(new HashMap<>());
//                }
//                prescription.getInformation().put(key, value);
//            }
//        }
//
//        return prescription;
//    }
//
//    // Helper method to parse a list of medicine objects (modify based on your JSON structure)
//    private List<Medicine> parseMedicineList(List<Object> medicineData) {
//        List<Medicine> medicines = new ArrayList<>();
//        for (Object medicineObj : medicineData) {
//            // Assuming medicine details are another map within the list
//            if (medicineObj instanceof Map) {
//                Medicine medicine = new Medicine();
//                @SuppressWarnings("unchecked")
//                Map<String, Object> medicineMap = (Map<String, Object>) medicineObj;
//                medicine.setMedicineName((String) medicineMap.get("medicineName"));
//                medicine.setMedicinePower((String) medicineMap.get("medicinePower"));
//                // Parse dosage and frequency assuming they are integers
//                medicine.setDosage((int) medicineMap.get("dosage"));
//                medicine.setFrequency((int) medicineMap.get("frequency"));
//                medicines.add(medicine);
//            }
//        }
//        return medicines;
//    }





    public String OCRSecondMethod(MultipartFile file) throws IOException, SQLException, TesseractException {
        String output = null;


        BufferedImage inputImage = ImageIO.read(file.getInputStream());

        double d = inputImage.getRGB(inputImage.getTileWidth( ) / 2,
                inputImage.getTileHeight() / 2 ) ;

        if ( d >= -1.4211511E7 && d < -7254228 ) {
           output = processImg( inputImage, 3f, -10f ) ;
        }
        else if ( d >= -7254228 && d < -2171170 ) {
            output = processImg( inputImage, 1.455f, -47f ) ;
        }
        else if ( d >= -2171170 && d < -1907998 ) {
            output = processImg( inputImage, 1.35f, -10f ) ;
        }
        else if ( d >= -1907998 && d < -257 ) {
            output = processImg( inputImage, 1.19f, 0.5f ) ;
        }
        else if ( d >= -257 && d < -1 ) {
            output =  processImg( inputImage, 1f, 0.5f ) ;
        }
        else if ( d >= -1 && d < 2 ) {
            output = processImg( inputImage, 1f, 0.35f ) ;
        }

        return output;
    }


    public static String processImg( BufferedImage inputImage, float scaleFactor, float offset )
            throws IOException, TesseractException
    {

        BufferedImage outputImage = new BufferedImage( 1050, 1024, inputImage.getType( ) ) ;
        Graphics2D grp = outputImage.createGraphics( ) ;

        grp.drawImage( inputImage, 0, 0, 1050, 1024, null ) ;
        grp.dispose( ) ;

        RescaleOp rescaleOutput = new RescaleOp( scaleFactor, offset, null ) ;


        BufferedImage finalOutputimage = rescaleOutput.filter(outputImage,null) ;

        ImageIO.write( finalOutputimage, " jpg ",
                new File( " src/main/java/com/ADA/AdvancedHealthAcademy/Service/output.jpg " ) ) ;

        Tesseract tesseractInstance = new Tesseract( ) ;
        tesseractInstance.setDatapath( "Tess4J/tessdata" ) ;

        String str = tesseractInstance.doOCR( finalOutputimage ) ;
        System.out.println( str ) ;
        return str;
    }


}
