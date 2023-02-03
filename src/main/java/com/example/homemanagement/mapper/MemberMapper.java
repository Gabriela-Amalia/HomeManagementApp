package com.example.homemanagement.mapper;

import com.example.homemanagement.dto.member.CreateMemberDto;
import com.example.homemanagement.dto.member.UpdateMemberDto;
import com.example.homemanagement.model.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public Member createMemberDtoToMember(CreateMemberDto createMemberDto) {
        return new Member(
                createMemberDto.getFirstName(),
                createMemberDto.getLastName(),
                createMemberDto.getEmail(),
                createMemberDto.getPassword()
        );
    }

    public Member updateMemberDtoToMember(UpdateMemberDto updateMemberDto) {
        return new Member(updateMemberDto.getId(),
                updateMemberDto.getFirstName(),
                updateMemberDto.getLastName(),
                updateMemberDto.getEmail(),
                updateMemberDto.getPassword(),
                updateMemberDto.getHousehold());
    }
}
