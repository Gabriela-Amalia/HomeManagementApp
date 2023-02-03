package com.example.homemanagement.dto.household;

import jakarta.validation.constraints.NotBlank;

public class CreateHouseholdDto {

    @NotBlank
    private String name;

    public CreateHouseholdDto() {
    }

    public CreateHouseholdDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
