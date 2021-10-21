package com.pet.manager.controller;

import com.pet.manager.controller.request.FeedRequest;
import com.pet.manager.controller.response.FeedResponse;
import com.pet.manager.controller.response.PetResponse;
import com.pet.manager.model.Feed;
import com.pet.manager.model.FoodType;
import com.pet.manager.model.Pet;
import com.pet.manager.service.PetService;
import org.apache.tomcat.jni.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FeedController {
    private final PetService petService;

    public FeedController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping(value = "/feeds/pets/{id}")
    public PetResponse addFeedToPet(@PathVariable(value = "id") String id, @RequestParam String time, @RequestParam FoodType food){
        Pet pet = petService.addFeedToPet(id, Feed.builder()
                .feedTime(LocalTime.parse(time))
                .foodType(food)
                .build());

        return pet.createPetRequest();
    }

    @GetMapping(value = "/feeds/pets/{id}")
    public List<FeedResponse> getFeedsByPedId(@PathVariable(value = "id") String id){
        List<Feed> feedList = petService.getFeedsByPedId(id);
        List<FeedResponse> feedResponseList = new ArrayList<>();

        for(Feed feed : feedList){
            feedResponseList.add(feed.createFeedResponse());
        }
        return feedResponseList;
    }
//Can't search for Feed to update because i can't generate an id for Feed

//    @PutMapping(value = "/feeds/pets/{id}")
//    public FeedResponse updateFeed(@PathVariable(value = "id") String petId, @RequestParam String time, @RequestParam FoodType food){
//        Feed feed = petService.updateFeedId(petId, Feed.builder()
//                .feedTime(LocalTime.parse(time))
//                .foodType(food)
//                .build());
//
//        return feed.createFeedResponse();
//    }
}
