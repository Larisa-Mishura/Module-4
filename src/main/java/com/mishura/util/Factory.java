package com.mishura.util;

import com.mishura.model.Workpiece;

public class Factory {

    private static final FuelProducer robot1 = new FuelProducer();
    private static final BaseConstructionMaker robot2 = new BaseConstructionMaker();
    private static  final BaseConstructionMaker robot3 = new BaseConstructionMaker();
    private static  final ChipProgrammer robot4 = new ChipProgrammer();
    private static  final Assembler robot5 = new Assembler();


    public static Workpiece makeWorkpiece(){
        final Workpiece workpiece = new Workpiece();
        robot1.work(workpiece, "Robot 1");
        robot2.work(workpiece, "Robot 2");
        robot3.work(workpiece, "Robot 3");
        robot4.work(workpiece, "Robot 4");
        robot5.work(workpiece, "Robot 5");
        return workpiece;
    }
}