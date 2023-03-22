package com.mishura.threads;

import com.mishura.model.Detail;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.log4j.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
public class FuelProducer extends Thread {

    private final Detail detail;

    private final String name;

    private final static Random random = new Random();

    private final static Logger LOGGER = Logger.getLogger(BaseConstructionMaker.class);

    @SneakyThrows
    @Override
    public void run() {
        String threadName = Thread.currentThread().getName() + " " + name;
        LOGGER.info(threadName + " has started work.");
        while (!detail.isReady()){
            int producedFuel = 500 + random.nextInt(500);
            detail.setProducedFuel(detail.getProducedFuel() + producedFuel);
            LOGGER.info(threadName + " has produced " + producedFuel + " of fuel, total amount of fuel - " + detail.getProducedFuel());
            LOGGER.info(threadName + " is transporting fuel.");
            TimeUnit.SECONDS.sleep(3);
        }
        LOGGER.info(threadName + " has finished work.");
    }
}
