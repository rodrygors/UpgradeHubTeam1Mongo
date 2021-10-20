package com.pet.manager.controller;

import com.pet.manager.controller.request.FeedRequest;
import com.pet.manager.controller.response.FeedResponse;
import com.pet.manager.controller.response.PetResponse;
import com.pet.manager.model.Feed;
import com.pet.manager.model.Pet;
import com.pet.manager.service.PetService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FeedController {
    private final PetService petService;

    public FeedController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping(value = "/feeds/pets/{id}")
    public PetResponse addFeedToPet(@PathVariable(value = "id") String id, @RequestBody FeedRequest feedRequest){
        Pet pet = petService.addFeedToPet(id, Feed.builder()
                .feedTme(feedRequest.getFeedTime())
                .foodType(feedRequest.getFoodType())
                .build());
        List<FeedResponse> feedResponseList = new ArrayList<>();
        for(Feed feed : pet.getFeedList()){
            feedResponseList.add(new FeedResponse(
                    feed.getId(),
                    feed.getFeedTme(),
                    feed.getFoodType()
            ));
        }
        return new PetResponse(
                pet.getId(),
                pet.getName(),
                pet.getType(),
                feedResponseList
        );
    }

    @GetMapping(value = "/feeds/pets/{id}")
    public List<FeedResponse> getFeedsByPedId(@PathVariable(value = "id") String id){
        List<Feed> feedList = petService.getFeedsByPedId(id);
        List<FeedResponse> feedResponseList = new ArrayList<>();

        for(Feed feed : feedList){
            feedResponseList.add(new FeedResponse(
                    feed.getId(),
                    feed.getFeedTme(),
                    feed.getFoodType()
            ));
        }
        return feedResponseList;
    }
}
