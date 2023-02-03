package com.example.homemanagement.service;

import com.example.homemanagement.exception.member.MemberAlreadyExistsException;
import com.example.homemanagement.exception.member.MemberNotFoundException;
import com.example.homemanagement.model.Member;
import com.example.homemanagement.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public Member create(Member member) {
        Optional<Member> memberWithSameEmail = memberRepository.findByEmail(member.getEmail());
        if(memberWithSameEmail.isPresent()) {
            throw new MemberAlreadyExistsException();
        }
        return memberRepository.save(member);
    }

    public Member update(Member member) {
        Optional<Member> existingMember = memberRepository.findById(member.getId());
        if(existingMember.isEmpty()) {
            throw new MemberNotFoundException();
        }
        Optional<Member> memberWithSameEmail =
                memberRepository.findByEmailAndIdNot(member.getEmail(), member.getId());
        if(memberWithSameEmail.isPresent()) {
            throw new MemberAlreadyExistsException();
        }

        return memberRepository.save(member);
    }

    public List<Member> get(String firstName, String lastName) {
        List<Member> members = new ArrayList<>();

        if(firstName != null && !firstName.isEmpty()) {
            if(lastName != null && !lastName.isEmpty()) {
                members = memberRepository.findByFirstNameAndLastName(firstName, lastName);
            } else {
                members = memberRepository.findByFirstName(firstName);
            }
        } else {
            if(lastName != null && !lastName.isEmpty()) {
                members = memberRepository.findByLastName(lastName);
            } else {
                members = memberRepository.findAll();
            }
        }
        return members;
    }

    public List<Member> getFromHousehold(Long householdId) {
        System.out.println(householdId);
        if(householdId != null)
            return memberRepository.findByHousehold_Id(householdId);
        else
            return memberRepository.findAll();
    }
}
