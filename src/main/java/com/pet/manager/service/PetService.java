package com.pet.manager.service;

import com.pet.manager.model.Feed;
import com.pet.manager.model.PetType;
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

    public List<Feed> getFeedsByPedId(String id) {
        return this.findById(id).getFeedList();
    }

    public List<Pet> findByType(String type) {
        return petRepo.findByType(PetType.valueOf(type));
    }

    public Pet updatePet(String id, String name, PetType type) {
        Pet pet = this.findById(id);
        pet.setName(name);
        pet.setType(type);
        return petRepo.save(pet);
    }

    public void deleteById(String id) {
        petRepo.deleteById(id);
    }

//    public Feed updateFeedId(String petId, Feed newFeed) {
//        return new Feed();
//    }
}
