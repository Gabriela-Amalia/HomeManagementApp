package com.example.homemanagement.dto.member;

import com.example.homemanagement.model.Household;

public class UpdateMemberDto extends CreateMemberDto {

    private long id;

    private Household household;

    public UpdateMemberDto() {
    }

    public UpdateMemberDto(String firstName, String lastName, String email, String password, long id, Household household) {
        super(firstName, lastName, email, password);
        this.id = id;
        this.household = household;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Household getHousehold() {
        return household;
    }

    public void setHousehold(Household household) {
        this.household = household;
    }
}
