package com.soumya.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soumya.entity.StudentEnqEntity;

public interface StudentEnqRepo extends JpaRepository<StudentEnqEntity, Integer> {

}
