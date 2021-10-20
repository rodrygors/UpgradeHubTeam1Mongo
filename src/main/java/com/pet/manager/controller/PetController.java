package com.pet.manager.controller;

import com.pet.manager.controller.request.PetRequest;
import com.pet.manager.controller.response.FeedResponse;
import com.pet.manager.controller.response.PetResponse;
import com.pet.manager.model.Feed;
import com.pet.manager.model.Pet;
import com.pet.manager.model.PetType;
import com.pet.manager.service.PetService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class PetController {
    public final PetService petServ;

    public PetController(PetService petServ) {
        this.petServ = petServ;
    }

    @GetMapping("/pets/{id}")
    public PetResponse getPetById(@PathVariable(value = "id") String id) {
        Pet pet = petServ.findById(id);
        List<FeedResponse> feedResponseList = new ArrayList<>();
        if(!pet.getFeedList().isEmpty() && pet.getFeedList() != null){
            for (Feed feed : pet.getFeedList()) {
                feedResponseList.add(new FeedResponse(
                        feed.getId(),
                        feed.getFeedTme(),
                        feed.getFoodType()
                ));
            }
        }
        return new PetResponse(
                pet.getId(),
                pet.getName(),
                pet.getType(),
                feedResponseList
        );
    }

    @GetMapping("/pets")
    public List<PetResponse> getPetByType(@RequestParam String type) {
        List<Pet> pets = petServ.findByType(type);
        List<PetResponse> petResponses = new ArrayList<>();
        for (Pet pet : pets) {
            List<FeedResponse> feedResponseList = new ArrayList<>();
            if(!pet.getFeedList().isEmpty() && pet.getFeedList() != null){
                for (Feed feed : pet.getFeedList()) {
                    feedResponseList.add(new FeedResponse(
                            feed.getId(),
                            feed.getFeedTme(),
                            feed.getFoodType()
                    ));
                }
            }
            petResponses.add(new PetResponse(
                    pet.getId(),
                    pet.getName(),
                    pet.getType(),
                    feedResponseList
            ));
        }
        return petResponses;
    }

    @GetMapping("/pets")
    public List<PetResponse> getPets() {
        List<Pet> pets = petServ.findAll();
        List<PetResponse> petResponses = new ArrayList<>();
        for (Pet pet : pets) {
            List<FeedResponse> feedResponseList = new ArrayList<>();
            if(!pet.getFeedList().isEmpty() && pet.getFeedList() != null){
                for (Feed feed : pet.getFeedList()) {
                    feedResponseList.add(new FeedResponse(
                            feed.getId(),
                            feed.getFeedTme(),
                            feed.getFoodType()
                    ));
                }
            }
            petResponses.add(new PetResponse(
                    pet.getId(),
                    pet.getName(),
                    pet.getType(),
                    feedResponseList
            ));
        }
        return petResponses;
    }

    @PostMapping("/pets")
    public PetResponse addPet(@RequestBody @Valid PetRequest petRequest) {
        Pet pet = petServ.addPet(Pet
                .builder()
                .name(petRequest.getName())
                .type(petRequest.getType())
                .build());
        List<FeedResponse> feedResponseList = new ArrayList<>();
        return new PetResponse(
                pet.getId(),
                pet.getName(),
                pet.getType(),
                new ArrayList<>()
        );
    }

}
