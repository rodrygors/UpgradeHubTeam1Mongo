package com.pet.manager.exception;

public class FeedNotFound extends RuntimeException{
    public FeedNotFound(){
        super("Feed not Found.");
    }
    public FeedNotFound(String message){
        super(message);
    }
}
