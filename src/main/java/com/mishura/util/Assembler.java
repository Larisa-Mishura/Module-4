package com.mishura.util;

import com.mishura.model.Workpiece;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
public class Assembler extends Thread {

    private Workpiece workpiece;

    private final String name;

    private final static Random random = new Random();

    private final static Logger LOGGER = Logger.getLogger(BaseConstructionMaker.class);

    @SneakyThrows
    @Override
    public void run() {
        String threadName = Thread.currentThread().getName() + " " + name;
        LOGGER.info(threadName + " has started work.");
        int completedPoints = 0;
        int fuelInIteration;
        int pointsInIteration;
        while (completedPoints < 100) {
            fuelInIteration = 350 + random.nextInt(350);
            workpiece.setSpentFuel(workpiece.getSpentFuel() + fuelInIteration);
            while (workpiece.getSpentFuel() > workpiece.getProducedFuel()){
                Thread.yield();
            }
            pointsInIteration = 1 + random.nextInt(9);
            completedPoints += pointsInIteration;
            LOGGER.info(threadName + " has made " + pointsInIteration + " points,  " + completedPoints + "% of required is made, " +
                    "total amount of spent fuel - " + workpiece.getSpentFuel());

            if (completedPoints < 100) {
                LOGGER.info(threadName + " is reloading for 1 second.");
                TimeUnit.SECONDS.sleep(1);
            } else {
                workpiece.setCreated(LocalDateTime.now());
                workpiece.setReady(true);
                LOGGER.info(threadName + " has finished work.");
            }
        }
    }
}
