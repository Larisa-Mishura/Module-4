package com.mishura.threads;

import com.mishura.model.Detail;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.log4j.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
public class ChipProgrammer extends Thread {

    private final Detail detail;

    private final String name;

    private final static Random random = new Random();

    private final static Logger LOGGER = Logger.getLogger(BaseConstructionMaker.class);

    @SneakyThrows
    @Override
    public void run() {
        String threadName = Thread.currentThread().getName() + " " + name;
        while (detail.getPointsOfBaseConstruction() < 100){
            Thread.yield();
        }
        LOGGER.info(threadName + " has started work.");
        int completedPoints = 0;
        int pointsInIteration;
        while (completedPoints < 100) {
            if (random.nextInt(10) < 3) {
                completedPoints = 0;
                detail.setCountOfBrokenChips(detail.getCountOfBrokenChips() + 1);
                LOGGER.info(threadName + " has broken chip.");
            } else {
                pointsInIteration = 25 + random.nextInt(11);
                completedPoints += pointsInIteration;
                LOGGER.info(threadName + " has made " + pointsInIteration + " points, " + completedPoints + "% of required is done. ");
            }
            if(completedPoints < 100) {
                LOGGER.info(threadName + " is reloading.");
                TimeUnit.SECONDS.sleep(1);
            } else {
                detail.setPointsOfChip(completedPoints);
                LOGGER.info(threadName + " has finished work.");
            }
        }
    }
}