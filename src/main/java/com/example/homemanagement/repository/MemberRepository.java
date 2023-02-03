package com.example.homemanagement.repository;

import com.example.homemanagement.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    Optional<Member> findByEmailAndIdNot(String email, long id);

    List<Member> findByFirstNameAndLastName(String firstName, String lastName);

    List<Member> findByFirstName(String firstName);

    List<Member> findByLastName(String lastName);

    List<Member> findByHousehold_Id(Long id);
}
