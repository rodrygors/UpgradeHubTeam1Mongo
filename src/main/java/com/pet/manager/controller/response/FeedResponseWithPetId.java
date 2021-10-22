package com.pet.manager.controller.response;

import com.pet.manager.model.FoodType;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedResponseWithPetId {
    private String id;
    private LocalTime feedTime;
    private FoodType foodType;
    private String petId;
}