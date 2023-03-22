package com.mishura.threads;

import com.mishura.model.Detail;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.log4j.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
public class BaseConstructionMaker extends Thread {

    private final Detail detail;

    private final String name;

    private final static Random random = new Random();

    private final static Logger LOGGER = Logger.getLogger(BaseConstructionMaker.class);

    @SneakyThrows
    @Override
    public void run() {
        String threadName = Thread.currentThread().getName() + " " + name;
        LOGGER.info(threadName + " has started work.");
        int pointsInOneIteration;
        while (detail.getPointsOfBaseConstruction() < 100) {
            pointsInOneIteration = 10 + random.nextInt(10);
            LOGGER.info(threadName + " has made " + pointsInOneIteration + " points. ");
            synchronized (detail.getMONITOR()) {
                if (detail.getPointsOfBaseConstruction() < 100) {
                    detail.setPointsOfBaseConstruction(detail.getPointsOfBaseConstruction() + pointsInOneIteration);
                }
            }
                if(detail.getPointsOfBaseConstruction() >= 100){
                    LOGGER.info("Base Construction is ready ("+ detail.getPointsOfBaseConstruction() + " points).");
                    LOGGER.info(threadName + " has finished work.");
                } else {
                    LOGGER.info(threadName + " is reloading for 2 seconds.");
                    TimeUnit.SECONDS.sleep(2);
                }
        }
    }
}
