package com.example.homemanagement.service;

import com.example.homemanagement.exception.household.HouseholdNotFoundException;
import com.example.homemanagement.exception.member.MemberAlreadyExistsException;
import com.example.homemanagement.exception.member.MemberNotFoundException;
import com.example.homemanagement.model.Household;
import com.example.homemanagement.model.Member;
import com.example.homemanagement.repository.HouseholdRepository;
import com.example.homemanagement.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    private final HouseholdRepository householdRepository;

    public MemberService(MemberRepository memberRepository, HouseholdRepository householdRepository) {
        this.memberRepository = memberRepository;
        this.householdRepository = householdRepository;
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

    public Member assign(long memberId, long householdId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberNotFoundException());
        Household household = householdRepository.findById(householdId).orElseThrow(() -> new HouseholdNotFoundException(householdId));
        member.setHousehold(household);
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

    public Optional<Member> get(long id) {
        if(!memberRepository.existsById(id)) {
            throw new MemberNotFoundException();
        }
        Optional<Member> member = memberRepository.findById(id);
        return member;
    }

    public List<Member> getFromHousehold(Long householdId) {
        if(householdId != null)
            return memberRepository.findByHousehold_Id(householdId);
        else
            return memberRepository.findAll();
    }
}
