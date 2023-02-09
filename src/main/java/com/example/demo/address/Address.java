package com.example.demo.address;

import com.example.demo.person.Person;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Address {
    
    @Id @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "address")
    private Person person;

    private String street;
    private String city;

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
}
