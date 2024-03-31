package com.cloudbees.trainbooking.repository;

import com.cloudbees.trainbooking.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<Section, Integer> {

    List<Section> findAllByTrainNumber(int trainNumber);

}
