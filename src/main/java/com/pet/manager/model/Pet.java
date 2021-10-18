package com.pet.manager.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

@Document
public class Pet {
    private String name;
    private PetType type;
}
