package com.mishura.util;

import com.mishura.model.Detail;
import com.mishura.threads.Assembler;
import com.mishura.threads.BaseConstructionMaker;
import com.mishura.threads.ChipProgrammer;
import com.mishura.threads.FuelProducer;

import java.time.LocalDateTime;
import java.util.List;

public class Factory {

    public static Detail makeItem(){
        final Detail detail = new Detail();

        final List<Thread> threads = List.of(
                new FuelProducer(detail, "Robot 1"),
                new BaseConstructionMaker(detail, "Robot 2"),
                new BaseConstructionMaker(detail, "Robot 3"),
                new ChipProgrammer(detail, "Robot 4"),
                new Assembler(detail, "Robot 5")
        );
        detail.setStart(LocalDateTime.now());
        threads.forEach(Thread::start);
        while (!detail.isReady()){
            Thread.yield();
        }
        return detail;
    }
}