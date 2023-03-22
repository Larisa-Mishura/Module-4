package com.mishura;

import com.mishura.service.Service;

public class Main {
    public static void main(String[] args) {

        final Service service = Service.getInstance();
        /*String id = service.createAndSave();
        System.out.println(service.getById(id).get().toString());*/
        System.out.println("~".repeat(40));

        System.out.println("Total amount of details - " + service.getStatistics().get(0).getCount());
        System.out.println("Total amount of produced fuel - " + service.getStatistics().get(0).getProducedFuel());
        System.out.println("Total amount of spent fuel - " + service.getStatistics().get(0).getSpentFuel());
        System.out.println("Count of broken chips - " + service.getStatistics().get(0).getCountOfBrokenChips());

    }
}
