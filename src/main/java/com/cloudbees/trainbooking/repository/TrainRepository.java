package com.cloudbees.trainbooking.repository;

import com.cloudbees.trainbooking.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainRepository extends JpaRepository<Train, Integer> {
}
