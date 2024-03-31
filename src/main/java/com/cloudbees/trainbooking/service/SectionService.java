package com.cloudbees.trainbooking.service;

import com.cloudbees.trainbooking.entity.Section;

import java.util.List;

public interface SectionService extends GenericService<Section> {
    List<Section> findAllBy(int trainNumber);
}
