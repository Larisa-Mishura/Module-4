package com.mishura.util;

import com.mishura.model.Workpiece;
import lombok.Setter;
import lombok.SneakyThrows;
import org.apache.log4j.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Setter
public class Assembler implements Runnable {

    private Workpiece workpiece;

    private final Random random = new Random();

    private final static Logger LOGGER = Logger.getLogger(BaseConstructionMaker.class);

    @SneakyThrows
    @Override
    public void run() {
        LOGGER.info(Thread.currentThread().getName() + " has started work.");
        int completedPoints = 0;
        int fuelInIteration;
        int pointsInIteration;
        while (completedPoints < 100) {
            fuelInIteration = 350 + random.nextInt(350);
            workpiece.setSpentFuel(workpiece.getSpentFuel() + fuelInIteration);
            while (workpiece.getSpentFuel() > workpiece.getProducedFuel()){
                Thread.yield();
            }
            pointsInIteration = 1 + random.nextInt(90);
            completedPoints += pointsInIteration;
            LOGGER.info(Thread.currentThread().getName() + " has made " + pointsInIteration + " points,  " + completedPoints + " of required is made. ");

            if (completedPoints < 100) {
                LOGGER.info(Thread.currentThread().getName() + " is reloading for 1 second.");
                TimeUnit.SECONDS.sleep(1);
            } else {
                workpiece.setReady(true);
                LOGGER.info(Thread.currentThread().getName() + " has finished work.");
            }
        }
    }


    @SneakyThrows
    public void work(Workpiece workpiece, String name) {
        this.setWorkpiece(workpiece);
        Thread thread = new Thread(this);
        thread.setName(name);
        thread.start();
    }
}
