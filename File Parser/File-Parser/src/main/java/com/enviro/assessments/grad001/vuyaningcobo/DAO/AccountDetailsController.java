package com.enviro.assessments.grad001.vuyaningcobo.DAO;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.UUID;
import javax.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;

import com.enviro.assessments.grad001.vuyaningcobo.entity.AccountDetails;
import com.enviro.assessments.grad001.vuyaningcobo.repository.AccountDetailsInterface;
import com.enviro.assessments.grad001.vuyaningcobo.repository.FileParser;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;


public class AccountDetailsController implements FileParser {
    @Autowired
    private AccountDetailsInterface repository;

    private String name;
    private String surname;
    private String imageFormat;
    private String imageData;;

    @Override
    public void parseCSV(File csvFile) {

        try(BufferedReader reader = new BufferedReader(new FileReader(csvFile));
            CSVReader csvReader = new CSVReader(reader);
            ) {

                String[] record;

                while((record = csvReader.readNext()) != null){
                    name = record[0];
                    surname = record[1];
                    imageFormat = record[2];
                    imageData = record[3];
                }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public File convertCSVDataToImage(String base64ImageData) {

        byte[] image = DatatypeConverter.parseBase64Binary(base64ImageData);

        String path = "/src/main/resources/"+UUID.randomUUID().toString()+"."+imageFormat;
        File file = new File(path);

        try {
            OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
            out.write(image);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    @Override
    public URI createImageLink(File fileImage) {
        return fileImage.toURI();
    }

    //create and persist Account Details Helper Method
    public void createAccount(){

        //create
        AccountDetails account = new AccountDetails();
        account.setName(name);
        account.setSurname(surname);
        account.setLink(createImageLink(convertCSVDataToImage(imageData)));

        //persist
        repository.save(account);
    }
}
