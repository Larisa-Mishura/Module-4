package com.mishura.util;

import com.mishura.model.Workpiece;
import lombok.Setter;
import lombok.SneakyThrows;
import org.apache.log4j.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Setter
public class FuelProducer implements Runnable{

    private Workpiece workpiece;

    private final Random random = new Random();

    private final static Logger LOGGER = Logger.getLogger(FuelProducer.class);

    @SneakyThrows
    @Override
    public void run() {
        LOGGER.info(Thread.currentThread().getName() + " has started work.");
        while (!workpiece.isReady()){
            int producedFuel = 500 + random.nextInt(500);
            workpiece.setProducedFuel(workpiece.getProducedFuel() + producedFuel);
            LOGGER.info(Thread.currentThread().getName() + " has produced " + producedFuel + " of fuel, total amount of fuel - " + workpiece.getProducedFuel());
            LOGGER.info(Thread.currentThread().getName() + " is transporting fuel.");
            TimeUnit.SECONDS.sleep(3);
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
