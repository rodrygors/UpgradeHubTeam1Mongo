package com.pet.manager.controller;

import com.pet.manager.controller.request.PetRequest;
import com.pet.manager.controller.response.PetResponse;
import com.pet.manager.model.Pet;
import com.pet.manager.service.PetService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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
    public PetResponse getPets(@PathVariable(value = "id") String id) {
        Pet pet = petServ.findById(id);

        return new PetResponse(
                pet.getId(),
                pet.getName(),
                pet.getType()
        );
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
    public PetResponse addPet(@RequestBody @Valid PetRequest petRequest) {
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
