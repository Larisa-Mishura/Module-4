package com.mishura.util;

import com.mishura.model.Workpiece;
import lombok.Setter;
import lombok.SneakyThrows;
import org.apache.log4j.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Setter
public class ChipProgrammer implements Runnable {

    private Workpiece workpiece;

    private final Random random = new Random();

    private final static Logger LOGGER = Logger.getLogger(ChipProgrammer.class);

    @SneakyThrows
    @Override
    public void run() {
        while (workpiece.getPointsOfBaseConstruction() < 100){
            Thread.yield();
        }
        LOGGER.info(Thread.currentThread().getName() + " has started work.");
        int completedPoints = 0;
        int pointsInIteration;
        while (completedPoints < 100) {
            if (random.nextInt(10) < 3) {
                completedPoints = 0;
                workpiece.setCountOfBrokenChips(workpiece.getCountOfBrokenChips() + 1);
                LOGGER.info(Thread.currentThread().getName() + " has broken chip.");
            } else {
                pointsInIteration = 25 + random.nextInt(11);
                completedPoints += pointsInIteration;
                LOGGER.info(Thread.currentThread().getName() + " has made " + pointsInIteration + " points,  " + completedPoints + " of required is done. ");
            }
            LOGGER.info(Thread.currentThread().getName() + " is reloading for 1 second.");
            TimeUnit.SECONDS.sleep(1);
        }
        LOGGER.info(Thread.currentThread().getName() + " has finished work.");
    }

    @SneakyThrows
    public void work(Workpiece workpiece, String name) {
        this.setWorkpiece(workpiece);
        Thread thread = new Thread(this);
        thread.setName("Robot №4");
        thread.start();
        thread.join();
    }
}