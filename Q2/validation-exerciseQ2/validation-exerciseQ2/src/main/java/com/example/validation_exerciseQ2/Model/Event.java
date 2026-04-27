package com.example.validation_exerciseQ2.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Event {

    @NotEmpty(message = "id can not be empty")
    @Size(min = 3 , message = "id must be more than 2 character")
    private String id ;

    @NotEmpty(message = "description can not be empty")
    @Size(min = 16 , message = "description must be more than 16 character")
    private String description ;

    @NotNull(message = "capacity can not be empty")
    @Min(26)
    private int capacity ;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate ;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate ;


}
