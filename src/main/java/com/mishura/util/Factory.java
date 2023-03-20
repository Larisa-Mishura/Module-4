package com.mishura.util;

import com.mishura.model.Workpiece;

import java.util.List;

public class Factory {




    public static Workpiece makeWorkpiece(){
        final Workpiece workpiece = new Workpiece();

        final List<Thread> threads = List.of(
                new FuelProducer(workpiece, "Robot 1"),
                new BaseConstructionMaker(workpiece, "Robot 2"),
                new BaseConstructionMaker(workpiece, "Robot 3"),
                new ChipProgrammer(workpiece, "Robot 4"),
                new Assembler(workpiece, "Robot 5")
        );

        threads.forEach(Thread::start);
        return workpiece;
    }
}