	package com.soumya.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soumya.entity.EnqStatusEntity;

public interface EnqStatusRepo extends JpaRepository<EnqStatusEntity, Integer> {

}
