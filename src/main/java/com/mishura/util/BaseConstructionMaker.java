package com.mishura.util;

import com.mishura.model.Workpiece;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import org.apache.log4j.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
public class BaseConstructionMaker extends Thread {

    private Workpiece workpiece;

    private final String name;

    private final static Random random = new Random();

    private final static Logger LOGGER = Logger.getLogger(BaseConstructionMaker.class);

    @SneakyThrows
    @Override
    public void run() {
        String threadName = Thread.currentThread().getName() + " " + name;
        LOGGER.info(threadName + " has started work.");
        int pointsInOneIteration;
        while (workpiece.getPointsOfBaseConstruction() < 100) {
            pointsInOneIteration = 10 + random.nextInt(10);
            LOGGER.info(threadName + " has made " + pointsInOneIteration + " points. ");
            synchronized (workpiece.getMONITOR()) {
                if (workpiece.getPointsOfBaseConstruction() < 100) {
                    workpiece.setPointsOfBaseConstruction(workpiece.getPointsOfBaseConstruction() + pointsInOneIteration);
                }
            }
                if(workpiece.getPointsOfBaseConstruction() >= 100){
                    LOGGER.info("Base Constraction is ready ("+ workpiece.getPointsOfBaseConstruction() + " points).");
                    LOGGER.info(threadName + " has finished work.");
                } else {
                    LOGGER.info(threadName + " is reloading for 2 seconds.");
                    TimeUnit.SECONDS.sleep(2);
                }
        }
    }
}
