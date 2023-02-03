package com.example.homemanagement.mapper;

import com.example.homemanagement.dto.household.CreateHouseholdDto;
import com.example.homemanagement.dto.household.UpdateHouseholdDto;
import com.example.homemanagement.model.Household;
import org.springframework.stereotype.Component;

@Component
public class HouseholdMapper {

    public Household createHouseholdDtoToHousehold(CreateHouseholdDto createHouseholdDto) {
        return new Household(
                createHouseholdDto.getName()
        );
    }

    public Household updateHouseholdDtoToHousehold(UpdateHouseholdDto updateHouseholdDto) {
        return new Household(updateHouseholdDto.getId(),
                updateHouseholdDto.getName());
    }
}
