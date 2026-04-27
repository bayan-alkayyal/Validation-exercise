package com.example.validation_exerciseQ1.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {

    @NotEmpty(message = "id should not be empty")
    @Size(min = 3 , message = "id should be more than 2")
    private String id ;

    @NotEmpty(message = "title should not be empty")
    @Size(min = 9 , message = "title should be more than 8 letters")
    private String title ;

    @NotEmpty(message = "description should not be empty")
    @Size(min = 16 , message = "description should be more than 15 letters" )
    private String description ;

    @NotEmpty(message = "status should not be empty")
    @Pattern(regexp = "^(not started|in progress|completed)$",
            message = "status must be: not started, in progress, or completed")
    private String status ;

    @NotEmpty(message = "company name should not be empty")
    @Size(min = 7 , message = "company name should be more than 6 letters")
    private String companyName ;




}
