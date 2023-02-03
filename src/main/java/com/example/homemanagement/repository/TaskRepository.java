package com.example.homemanagement.repository;

import com.example.homemanagement.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByMember_Household_Id(long householdId);

    List<Task> findByMember_Id(Long memberId);

    List<Task> findByMember_Household_IdAndMember_Id(long householdId, long memberId);


}
