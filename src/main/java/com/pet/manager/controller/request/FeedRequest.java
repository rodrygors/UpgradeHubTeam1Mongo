package com.pet.manager.controller.request;

import com.pet.manager.model.FoodType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedRequest {
    private LocalDateTime feedTime;
    private FoodType foodType;
}
