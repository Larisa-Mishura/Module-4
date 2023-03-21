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
public class FuelProducer extends Thread {

    private Workpiece workpiece;

    private final String name;

    private final static Random random = new Random();

    private final static Logger LOGGER = Logger.getLogger(BaseConstructionMaker.class);

    @SneakyThrows
    @Override
    public void run() {
        String threadName = Thread.currentThread().getName() + " " + name;
        LOGGER.info(threadName + " has started work.");
        while (!workpiece.isReady()){
            int producedFuel = 500 + random.nextInt(500);
            workpiece.setProducedFuel(workpiece.getProducedFuel() + producedFuel);
            LOGGER.info(threadName + " has produced " + producedFuel + " of fuel, total amount of fuel - " + workpiece.getProducedFuel());
            LOGGER.info(threadName + " is transporting fuel.");
            TimeUnit.SECONDS.sleep(3);
        }
        LOGGER.info(threadName + " has finished work.");
    }
}
