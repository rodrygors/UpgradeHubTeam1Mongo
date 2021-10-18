package com.pet.manager.controller;

import com.pet.manager.controller.request.PetRequest;
import com.pet.manager.controller.response.PetResponse;
import com.pet.manager.model.Pet;
import com.pet.manager.service.PetService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PetController {
    public final PetService petServ;

    public PetController(PetService petServ) {
        this.petServ = petServ;
    }

    @GetMapping("/pets")
    public List<PetResponse> getPets() {
        List<Pet> pets = petServ.findAll();
        List<PetResponse> petResponses = new ArrayList<>();
        for (Pet pet : pets) {
            petResponses.add(new PetResponse(
                    pet.getId(),
                    pet.getName(),
                    pet.getType()
            ));
        }
        return petResponses;
    }

    @PostMapping("/pets")
    public PetResponse addPet(@RequestBody PetRequest petRequest) {
        Pet pet = petServ.addPet(Pet
                .builder()
                .name(petRequest.getName())
                .type(petRequest.getType())
                .build());

        return new PetResponse(
                pet.getId(),
                pet.getName(),
                pet.getType()
        );
    }

}
