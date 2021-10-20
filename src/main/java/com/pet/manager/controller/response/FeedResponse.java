package com.pet.manager.controller.response;

import com.pet.manager.model.FoodType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedResponse {
    private String id;
    private LocalDateTime feedTme;
    private FoodType foodType;
}
