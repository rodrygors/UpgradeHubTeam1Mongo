package com.pet.manager.service;

import com.pet.manager.exception.DuplicatePetException;
import com.pet.manager.exception.FeedNotFound;
import com.pet.manager.exception.PetNotFound;
import com.pet.manager.model.Feed;
import com.pet.manager.model.Pet;
import com.pet.manager.model.PetType;
import com.pet.manager.repository.PetRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.util.Pair;
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

    public Feed updateFeedId(String feedId, String petId, Feed newFeed) {
        Pet pet = this.findById(petId);
        for (Feed feed : pet.getFeedList()) {
            if (feed.getId().equals(feedId)) {
                feed.setFeedTime(newFeed.getFeedTime());
                feed.setFoodType(newFeed.getFoodType());
                petRepo.save(pet);
                return feed;
            }
        }
        throw new FeedNotFound();
    }

    public Pet getPetByName(String name) {
        return petRepo.findPetByName(name).orElseThrow(PetNotFound::new);
    }

//    public Pair<String, Feed> findFeedById(String id) {
//        Pet pet = petRepo.findByFeedId(id).orElseThrow(FeedNotFound::new);
//        for (Feed feed : pet.getFeedList()) {
//            if (feed.getId().equals(id)) {
//                Pair<String, Feed> pair = new Pair<>(pet.getId(), feed);
//                break;
//            }
//        }
//        return pair;
//    }
}
