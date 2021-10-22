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
        return pet.createPetRequest();
    }

    @GetMapping("/pets-by-type")
    public List<PetResponse> getPetByType(@RequestParam String type) {
        List<Pet> pets = petServ.findByType(type);
        List<PetResponse> petResponses = new ArrayList<>();
        for (Pet pet : pets) {
            petResponses.add(pet.createPetRequest());
        }
        return petResponses;
    }

    @GetMapping("/pets-by-name")
    public PetResponse getPetByName(@RequestParam String name){
        Pet pet = petServ.getPetByName(name);
        return pet.createPetRequest();
    }

    @GetMapping("/pets")
    public List<PetResponse> getPets() {
        List<Pet> pets = petServ.findAll();
        List<PetResponse> petResponseList = new ArrayList<>();
        for (Pet pet : pets) {
            petResponseList.add(pet.createPetRequest());
        }
        return petResponseList;
    }

    @PostMapping("/pets")
    public PetResponse addPet(@RequestBody() @Valid PetRequest petRequest) {
        Pet pet = petServ.addPet(Pet
                .builder()
                .name(petRequest.getName())
                .type(petRequest.getType())
                .build());
        return pet.createPetRequest();
    }

    @PutMapping(value = "/pets/{id}")
    public PetResponse updatePet(@RequestBody PetRequest petRequest, @PathVariable(value = "id") String id){
        Pet pet = petServ.updatePet(
                id,
                petRequest.getName(),
                petRequest.getType()
                );

        return pet.createPetRequest();
    }

    @DeleteMapping(value = "/pets/{id}")
    public void deletePetById(@PathVariable(value = "id") String id){
        petServ.deleteById(id);
    }
}
