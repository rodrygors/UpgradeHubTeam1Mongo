package com.pet.manager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pet.manager.controller.response.FeedResponse;
import com.pet.manager.controller.response.PetResponse;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pet {
    @Id
    private String id;
    @NotBlank(message = "Name is mandatory")
    @Indexed(unique = true)
    private String name;
    @Indexed(unique = false)
    private PetType type;
    private List<Feed> feedList = new ArrayList<>();

    @JsonIgnore
    public PetResponse createPetRequest(){
        List<FeedResponse> feedResponseList = new ArrayList<>();
        if(this.feedList!=null && !this.feedList.isEmpty()){
            for(Feed feed: this.feedList){
                feedResponseList.add(new FeedResponse(
                        feed.getId(),
                        feed.getFeedTime(),
                        feed.getFoodType()
                ));
            }
        }
        return new PetResponse(
                this.getId(),
                this.getName(),
                this.getType(),
                feedResponseList
        );
    }
}