package com.example.homemanagement.controller;

import com.example.homemanagement.dto.member.CreateMemberDto;
import com.example.homemanagement.dto.member.UpdateMemberDto;
import com.example.homemanagement.mapper.MemberMapper;
import com.example.homemanagement.model.Member;
import com.example.homemanagement.service.HouseholdService;
import com.example.homemanagement.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper memberMapper;

    private final HouseholdService householdService;

    public MemberController(MemberService memberService, MemberMapper memberMapper, HouseholdService householdService) {
        this.memberService = memberService;
        this.memberMapper = memberMapper;
        this.householdService = householdService;
    }

    @PostMapping
    public ResponseEntity<Member> create(
            @RequestBody
            @Valid
            CreateMemberDto createMemberDto
    ) {
        Member member = memberMapper.createMemberDtoToMember(createMemberDto);
        Member createdMember = memberService.create(member);
        return ResponseEntity.created(URI.create("/members/" + createdMember.getId()))
                .body(createdMember);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> update(
            @PathVariable
            long id,
            @RequestBody
            @Valid
            UpdateMemberDto updateMemberDto) {
        if(id != updateMemberDto.getId()) {
            throw new RuntimeException("IDs must match");
        }

        Member member = memberMapper.updateMemberDtoToMember(updateMemberDto);
        Member updatedMember = memberService.update(member);
        return ResponseEntity.ok()
                .body(updatedMember);
    }

    @GetMapping
    public List<Member> get(
            @RequestParam(required = false)
            Long householdId) {
        return memberService.getFromHousehold(householdId);
    }
}
