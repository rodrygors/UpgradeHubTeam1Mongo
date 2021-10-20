package com.pet.manager.service;

import com.pet.manager.model.Feed;
import org.springframework.dao.DuplicateKeyException;
import com.pet.manager.exception.DuplicatePetException;
import com.pet.manager.exception.PetNotFound;
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

        try {
            return petRepo.save(pet);
        } catch (DuplicateKeyException e) {
            throw new DuplicatePetException();
        }
    }

    public Pet findById(String id) {
        return petRepo.findById(id).orElseThrow(PetNotFound::new);
    }

    public Pet addFeedToPet(String petId, Feed feed) {
        Pet pet = this.findById(petId);
        pet.getFeedList().add(feed);
        petRepo.save(pet);
        return pet;
    }
}
