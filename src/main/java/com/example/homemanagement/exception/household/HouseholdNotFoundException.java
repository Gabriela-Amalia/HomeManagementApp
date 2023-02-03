package com.example.homemanagement.exception.household;

public class HouseholdNotFoundException extends RuntimeException {

    public HouseholdNotFoundException(long id) { super("Could not find household with id = " + id);
    }
}
