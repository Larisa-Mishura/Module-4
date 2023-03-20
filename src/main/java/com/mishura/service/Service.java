package com.mishura.service;

import com.mishura.DTO.StatisticsDTO;
import com.mishura.model.Workpiece;
import com.mishura.repository.WorkpieceRepository;
import com.mishura.util.Factory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Service {

    private final WorkpieceRepository repository;

    private static Service instance;

    public static Service getInstance() {
        if (instance == null) {
            instance = new Service(WorkpieceRepository.getInstance());
        }
        return instance;
    }

    public Service(WorkpieceRepository repository) {
        this.repository = repository;
    }

    public String createAndSave(){
        Workpiece workpiece = Factory.makeWorkpiece();
        while (!workpiece.isReady()){
            Thread.yield();
        }
        repository.save(workpiece);
        return workpiece.getId();
    }

    public Optional<Workpiece> getById(final String workpieceId){
        return repository.getById(workpieceId);
    }

    public List<Workpiece> getAll(){
        return repository.getAll();
    }


    public List<String> getWorkpiecesId(){
        return repository.getAll().stream()
                .map(Workpiece::getId)
                .collect(Collectors.toList());
    }

    public List<StatisticsDTO> getStatistics(){
        return repository.getStatistics();
    }
}