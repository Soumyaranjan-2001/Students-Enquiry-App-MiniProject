package com.soumya.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soumya.entity.CourseEntity;

public interface CourseRepo extends JpaRepository<CourseEntity, Integer> {

}
