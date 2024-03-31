package com.cloudbees.trainbooking.service.impl;

import com.cloudbees.trainbooking.entity.Schedule;
import com.cloudbees.trainbooking.entity.Section;
import com.cloudbees.trainbooking.entity.Train;
import com.cloudbees.trainbooking.exception.NotFoundException;
import com.cloudbees.trainbooking.model.schedule.ScheduleResponse;
import com.cloudbees.trainbooking.model.section.SectionResponse;
import com.cloudbees.trainbooking.model.train.TrainResponse;
import com.cloudbees.trainbooking.repository.ScheduleRepository;
import com.cloudbees.trainbooking.service.ScheduleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository repository;

    public ScheduleServiceImpl(ScheduleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ScheduleResponse> findSchedule(String from, String to) {

        var resultList = repository.findAllBy(from, to);

        var scheduleSet = new HashSet<Schedule>();
        var trainMap = new HashMap<Integer, TrainResponse>();
        var sectionMap = new HashMap<Integer, List<SectionResponse>>();

        for (var result : resultList) {

            var res1 = result.stream()
                    .filter(e -> e instanceof Schedule)
                    .map(e -> (Schedule) e)
                    .toList();
            scheduleSet.addAll(res1);

            var res2 = result.stream()
                    .filter(t -> t instanceof Train)
                    .map(t -> (Train) t)
                    .map(TrainResponse::new)
                    .collect(Collectors.toMap(TrainResponse::getNumber, t -> t));
            trainMap.putAll(res2);

            var res3 = result.stream()
                    .filter(s -> s instanceof Section)
                    .map(s -> (Section) s)
                    .toList();
            res3.forEach(s -> {
                        List<SectionResponse> sectionList;

                        if (sectionMap.containsKey(s.getTrainNumber())) {
                            sectionList = sectionMap.get(s.getTrainNumber());
                        } else {
                            sectionList = new ArrayList<>();
                            sectionMap.put(s.getTrainNumber(), sectionList);
                        }

                        sectionList.add(new SectionResponse(s));
                        sectionMap.put(s.getTrainNumber(), sectionList);
                    });
        }

        for (var entry : trainMap.entrySet()) {
            var sectionRespList = sectionMap.get(entry.getKey());
            entry.getValue().setSections(sectionRespList);
        }

        var responseList = new ArrayList<ScheduleResponse>();
        for (var schedule : scheduleSet) {
            var response = new ScheduleResponse(schedule);
            response.setTrainDetails(trainMap.get(schedule.getTrainNumber()));
            responseList.add(response);
        }

        return responseList;
    }

    @Override
    public Schedule update(Schedule schedule) {
        return repository.save(schedule);
    }

    @Override
    public Schedule create(Schedule schedule) {
        return repository.save(schedule);
    }

    @Override
    public Schedule get(int id) throws NotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("No schedule found with id: " + id));
    }

    @Override
    public List<Schedule> getAll() {
        return repository.findAll();
    }
}
