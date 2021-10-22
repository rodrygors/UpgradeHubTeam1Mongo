package com.pet.manager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pet.manager.controller.request.FeedRequest;
import com.pet.manager.controller.response.FeedResponse;
import com.pet.manager.controller.response.FeedResponseWithPetId;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Feed {
    private String id ;
    private LocalTime feedTime;
    private FoodType foodType;

    @JsonIgnore
    public FeedResponse createFeedResponse(){
        return new FeedResponse(
                this.getId(),
                this.getFeedTime(),
                this.getFoodType()
        );
    }
    @JsonIgnore
    public FeedResponseWithPetId createFeedResponseWithPetId(String petId){
        return new FeedResponseWithPetId(
                this.getId(),
                this.getFeedTime(),
                this.getFoodType(),
                petId
        );
    }
}
