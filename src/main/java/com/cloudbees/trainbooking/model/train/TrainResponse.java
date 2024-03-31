package com.cloudbees.trainbooking.model.train;

import com.cloudbees.trainbooking.entity.Section;
import com.cloudbees.trainbooking.entity.Train;
import com.cloudbees.trainbooking.model.section.SectionResponse;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class TrainResponse extends Train {

    private List<SectionResponse> sections;

    public TrainResponse() {}

    public TrainResponse(Train train) {
        setNumber(train.getNumber());
        setName(train.getName());
        setFromStation(train.getFromStation());
        setToStation(train.getToStation());
    }

    public TrainResponse(Train train, List<Section> sections) {
        this(train);
        this.sections = sections.stream().map(SectionResponse::new).toList();
    }

}
