package com.example.demo.person;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class PersonController {

    @Autowired
    private PersonService personService;


    @PutMapping("people/{personId}/addPhone/{phoneId}")
    public Person addPhone(@PathVariable("personId") Long personId
                            ,@PathVariable("phoneId") Long phoneId) {
        return personService.addPhone(personId, phoneId);
    }


    @PostMapping("/people/")
    @ResponseStatus(HttpStatus.CREATED)
    public Person save(@Valid @RequestBody Person person) {
        return personService.save(person);
    }    

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
