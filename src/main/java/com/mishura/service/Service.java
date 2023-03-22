package com.mishura.service;

import com.mishura.DTO.StatisticsDTO;
import com.mishura.model.Detail;
import com.mishura.repository.Repository;
import com.mishura.util.Factory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Service {

    private final Repository repository;

    private static Service instance;

    public static Service getInstance() {
        if (instance == null) {
            instance = new Service(Repository.getInstance());
        }
        return instance;
    }

    public Service(Repository repository) {
        this.repository = repository;
    }

    public String createAndSave(){
        Detail detail = Factory.makeItem();
        /*while (!item.isReady()){
            Thread.yield();
        }*/
        repository.save(detail);
        return detail.getId();
    }

    public Optional<Detail> getById(final String itemId){
        return repository.getById(itemId);
    }

    public List<Detail> getAll(){
        return repository.getAll();
    }


    public List<String> getItemsId(){
        return repository.getAll().stream()
                .map(Detail::getId)
                .collect(Collectors.toList());
    }

    public List<StatisticsDTO> getStatistics(){
        return repository.getStatistics();
    }
}