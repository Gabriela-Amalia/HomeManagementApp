package com.example.homemanagement.dto.household;

public class UpdateHouseholdDto extends CreateHouseholdDto{

    private long id;

    public UpdateHouseholdDto() {
    }

    public UpdateHouseholdDto(String name, long id) {
        super(name);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
