package com.example.homemanagement.controller;

import com.example.homemanagement.dto.household.CreateHouseholdDto;
import com.example.homemanagement.dto.household.UpdateHouseholdDto;
import com.example.homemanagement.mapper.HouseholdMapper;
import com.example.homemanagement.model.Household;
import com.example.homemanagement.service.HouseholdService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/households")
public class HouseholdController {

    private final HouseholdService householdService;

    private final HouseholdMapper householdMapper;

    public HouseholdController(HouseholdService householdService, HouseholdMapper householdMapper) {
        this.householdService = householdService;
        this.householdMapper = householdMapper;
    }

    @PostMapping("")
    public ResponseEntity<Household> create(
            @RequestBody
            @Valid
            CreateHouseholdDto createHouseholdDto
    ) {
        Household household = householdMapper.createHouseholdDtoToHousehold(createHouseholdDto);
        Household createdHousehold = householdService.create(household);
        return ResponseEntity.created(URI.create("/households" + createdHousehold.getId()))
                .body(createdHousehold);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Household> update(
            @PathVariable
            long id,
            @RequestBody
            @Valid
            UpdateHouseholdDto updateHouseholdDto) {

        if(id != updateHouseholdDto.getId()) {
            throw new RuntimeException("IDs must match");
        }

        Household household = householdMapper.updateHouseholdDtoToHousehold(updateHouseholdDto);
        Household updatedHousehold = householdService.update(household);
        return ResponseEntity.ok()
                .body(updatedHousehold);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Household>> get(
            @PathVariable
            long id) {
        Optional<Household> household = householdService.get(id);
        return ResponseEntity.ok()
                .body(household);
    }
}
