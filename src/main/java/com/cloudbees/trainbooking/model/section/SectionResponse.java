package com.cloudbees.trainbooking.model.section;

import com.cloudbees.trainbooking.entity.Section;
import lombok.Data;

@Data
public class SectionResponse {

    private int Id;
    private String name;
    private int totalSeats;

    public SectionResponse() {}

    public SectionResponse(Section section) {
        Id = section.getId();
        name = section.getName();
        totalSeats = section.getTotalSeats();
    }

}
