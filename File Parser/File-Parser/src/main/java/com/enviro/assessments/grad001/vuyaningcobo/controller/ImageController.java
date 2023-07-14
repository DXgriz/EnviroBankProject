package com.enviro.assessments.grad001.vuyaningcobo.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enviro.assessments.grad001.vuyaningcobo.DAO.AccountDetailsController;
import com.enviro.assessments.grad001.vuyaningcobo.entity.AccountDetails;
import com.enviro.assessments.grad001.vuyaningcobo.repository.AccountDetailsInterface;


@RestController
@RequestMapping("/v1/api/image")
public class ImageController {

    @Autowired
    private AccountDetailsInterface repository;

    @GetMapping(value = "/{name}/{surname}/{\\w\\.\\w}")
    public FileSystemResource getHttpImageLink(@PathVariable String name, @PathVariable String surname){

       FileSystemResource fileSyst = null;
        if (name!=null && surname!=null) {

            List<AccountDetails> det = repository.findAll();
            for (AccountDetails accountDetails : det) {

                if (det != null) {
                    if (name.equalsIgnoreCase(accountDetails.getName())
                    ||surname.equalsIgnoreCase(accountDetails.getSurname())) {
                        fileSyst = new FileSystemResource(new File(accountDetails.getLink()));
                        return fileSyst;
                    } else {
                        return null;
                    }
                }
            }
       }
        return fileSyst;
    }
}
