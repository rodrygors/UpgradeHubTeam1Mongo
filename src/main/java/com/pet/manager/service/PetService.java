package com.pet.manager.service;

import com.pet.manager.repository.PetRepository;
import org.springframework.stereotype.Service;

@Service
public class PetService {
    private final PetRepository petRepo;

    public PetService(PetRepository petRepo) {
        this.petRepo = petRepo;
    }
}
