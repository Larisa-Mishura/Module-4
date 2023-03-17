package com.mishura.util;

import com.mishura.model.Workpiece;
import lombok.Setter;
import lombok.SneakyThrows;
import org.apache.log4j.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Setter
public class BaseConstructionMaker implements Runnable {

    private Workpiece workpiece;

    private final Random random = new Random();

    private final static Logger LOGGER = Logger.getLogger(BaseConstructionMaker.class);

    @SneakyThrows
    @Override
    public void run() {
        LOGGER.info(Thread.currentThread().getName() + " has started work.");
        int percentOfDoneForOneIteration;
        while (workpiece.getPointsOfBaseConstruction() < 100) {
            percentOfDoneForOneIteration = 10 + random.nextInt(10);
            LOGGER.info(Thread.currentThread().getName() + " has made " + percentOfDoneForOneIteration + " % of base constraction. ");
            synchronized (workpiece.getMONITOR()) {
                if (workpiece.getPointsOfBaseConstruction() < 100) {
                    workpiece.setPointsOfBaseConstruction(workpiece.getPointsOfBaseConstruction() + percentOfDoneForOneIteration);
                }
            }
                if(workpiece.getPointsOfBaseConstruction() >= 100){
                    LOGGER.info("Base Constraction is ready ("+ workpiece.getPointsOfBaseConstruction() + " %).");
                } else {
                    LOGGER.info(Thread.currentThread().getName() + " is reloading for 2 seconds.");
                    TimeUnit.SECONDS.sleep(2);
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
