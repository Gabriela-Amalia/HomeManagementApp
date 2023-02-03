package com.example.homemanagement.repository;

import com.example.homemanagement.model.Household;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HouseholdRepository extends JpaRepository<Household, Long> {

    Optional<Household> findById(long id);

    Boolean existsById(long id);
}
