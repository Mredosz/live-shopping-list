package com.pjatk.userservice.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document("user")
public class User {
    @Id
    private String id;

    @Indexed(unique = true)
    private String username;
    private String password;
    private List<String> shoppingListsIds;
}
