package com.pet.manager.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
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
    private List<Feed> feedList;
}