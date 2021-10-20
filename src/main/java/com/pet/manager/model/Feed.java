package com.pet.manager.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Feed {
    @Id
    private String id;
    private LocalDateTime feedTme;
    private FoodType foodType;
}
