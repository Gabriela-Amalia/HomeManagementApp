package com.example.homemanagement.service;

import com.example.homemanagement.exception.household.HouseholdNotFoundException;
import com.example.homemanagement.model.Household;
import com.example.homemanagement.model.Member;
import com.example.homemanagement.repository.HouseholdRepository;
import com.example.homemanagement.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HouseholdService {

    private final HouseholdRepository householdRepository;

    private final MemberRepository memberRepository;

    public HouseholdService(HouseholdRepository householdRepository, MemberRepository memberRepository) {
        this.householdRepository = householdRepository;
        this.memberRepository = memberRepository;
    }

    public Household create(Household household) {
        return householdRepository.save(household);
    }

    public Household update(Household household) {
        Optional<Household> existingHousehold = householdRepository.findById(household.getId());
        if(existingHousehold.isEmpty()) {
            throw new HouseholdNotFoundException(household.getId());
        }

        return householdRepository.save(household);
    }

    public Optional<Household> get(long id) {
        if(!householdRepository.existsById(id)) {
            throw new HouseholdNotFoundException(id);
        }
        Optional<Household> household = householdRepository.findById(id);
        return household;
    }

    public List<Member> getMembers(long id) {
        System.out.println(memberRepository.findAll().size());
        return memberRepository.findAll().stream().filter(m -> m.getHousehold() != null && m.getHousehold().getId() == id).collect(Collectors.toList());
    }
}
