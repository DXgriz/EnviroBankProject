package com.enviro.assessments.grad001.vuyaningcobo.entity;

import java.net.URI;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="account_details")
public class AccountDetails {
    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String surname;
    private String imageFormat;
    private URI httpImageLink; 

    public AccountDetails(){
        super();

    }

    public void setID(int id){
        this.id = id;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setSurname(String surname){
        this.surname=surname;
    }
    public void setImageFormat(String imgF){
        this.imageFormat = imgF;
    }
    public void setLink(URI link){
        this.httpImageLink=link;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getSurname(){
        return surname;
    }
    public String getImageFormat(){
        return imageFormat;
    }
    public URI getLink(){
        return  httpImageLink;
    }

}
 