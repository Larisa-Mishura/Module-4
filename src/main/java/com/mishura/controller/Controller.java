package com.mishura.controller;

import com.mishura.model.Workpiece;
import com.mishura.repository.WorkpieceRepository;
import com.mishura.service.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Controller {

    private final Service service;

    private static Controller instance;

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller(Service.getInstance());
        }
        return instance;
    }

    public Controller(Service service) {
        this.service = service;
    }

    public String create(){
        final Workpiece workpiece = service.createAndSave();
        return workpiece.getId();//TODO
    }

    public Optional<Workpiece> get(final String workpieceId){
        return service.getById(workpieceId);
    }

    public List<String> getWorkpiecesId(){
        return service.getAll().stream()
                .map(Workpiece::getId)
                .collect(Collectors.toList());
    }
}