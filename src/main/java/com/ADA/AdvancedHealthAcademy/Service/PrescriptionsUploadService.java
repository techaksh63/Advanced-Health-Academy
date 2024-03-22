package com.ADA.AdvancedHealthAcademy.Service;

import com.ADA.AdvancedHealthAcademy.Entity.PrescriptionsUpload;
import com.ADA.AdvancedHealthAcademy.Repository.PrescriptionsUploadRepository;
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

@Service
public class PrescriptionsUploadService {
    @Autowired
    private PrescriptionsUploadRepository repository;

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
            return output;
        }
        catch ( TesseractException e ) {
            e.printStackTrace( ) ;
        }
        return "Error";
    }



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
