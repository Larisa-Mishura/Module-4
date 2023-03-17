package com.mishura.service;

import com.mishura.model.Workpiece;
import com.mishura.repository.WorkpieceRepository;
import com.mishura.util.Factory;

import java.util.List;
import java.util.Optional;

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

    public Workpiece createAndSave(){
        Workpiece workpiece = create();
        repository.save(workpiece);
        return workpiece;//TODO
    }

    public Workpiece create(){
        Workpiece workpiece = Factory.makeWorkpiece();
        while (!workpiece.isReady()){
            Thread.yield();
        }

        return workpiece;//TODO
    }

    public Optional<Workpiece> getById(final String workpieceId){
        return repository.getById(workpieceId);
    }

    public List<Workpiece> getAll(){
        return repository.getAll();
    }
}