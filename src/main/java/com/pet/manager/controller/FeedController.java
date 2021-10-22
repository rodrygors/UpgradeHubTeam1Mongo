package com.pet.manager.controller;

import com.pet.manager.controller.response.FeedResponse;
import com.pet.manager.controller.response.FeedResponseWithPetId;
import com.pet.manager.controller.response.PetResponse;
import com.pet.manager.model.Feed;
import com.pet.manager.model.FoodType;
import com.pet.manager.model.Pet;
import com.pet.manager.service.PetService;
import lombok.Getter;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class FeedController {
    private final PetService petService;

    public FeedController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping(value = "/feeds/pets/{id}")
    public PetResponse addFeedToPet(@PathVariable(value = "id") String petId, @RequestParam String time, @RequestParam FoodType food) {
        Pet pet = petService.addFeedToPet(petId, Feed.builder()
                .id(UUID.randomUUID().toString())
                .feedTime(LocalTime.parse(time))
                .foodType(food)
                .build());

        return pet.createPetRequest();
    }

    @GetMapping(value = "/feeds/pets/{id}")
    public List<FeedResponse> getFeedsByPedId(@PathVariable(value = "id") String id) {
        List<Feed> feedList = petService.getFeedsByPedId(id);
        List<FeedResponse> feedResponseList = new ArrayList<>();

        for (Feed feed : feedList) {
            feedResponseList.add(feed.createFeedResponse());
        }
        return feedResponseList;
    }


//    @GetMapping(value = "/feeds/{id}")
//    public FeedResponseWithPetId getFeedById(@PathVariable(value = "id") String id){
//        Pair<String, Feed> feed = petService.findFeedById(id);
//    }

    @PutMapping(value = "/feeds/{feedId}/pets/{petId}")
    public FeedResponse updateFeed(@PathVariable(value = "feedId") String feedId,@PathVariable(value = "petId") String petId, @RequestParam String time, @RequestParam FoodType food){
        Feed feed = petService.updateFeedId(feedId, petId, Feed.builder()
                .feedTime(LocalTime.parse(time))
                .foodType(food)
                .build());

        return feed.createFeedResponse();
    }
//    @DeleteMapping(value = "/feed/{id}")
//    public void deleteFeedById(@PathVariable(value = "id") String id){
//
//    }
}
