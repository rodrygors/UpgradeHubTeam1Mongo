package com.pet.manager.service;

import com.pet.manager.model.Pet;
import com.pet.manager.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    private final PetRepository petRepo;

    public PetService(PetRepository petRepo) {
        this.petRepo = petRepo;
    }

    public List<Pet> findAll() {
        return petRepo.findAll();
    }

    public Pet addPet(Pet pet) {
        return petRepo.save(pet);
    }
}
