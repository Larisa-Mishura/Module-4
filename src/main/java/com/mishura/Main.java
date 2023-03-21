package com.mishura;

import com.mishura.service.Service;

public class Main {
    public static void main(String[] args) {

        final Service service = Service.getInstance();
        String id = service.createAndSave();
        System.out.println(id);

        /*String id2 = controller.create();
        System.out.println(id2);
*/
        /*String id2 = controller.create();
        System.out.println(id2);*/

       /* System.out.println("Total amount of produced fuel - " + service.getStatistics().get(0).getSpentFuel());
        System.out.println("Total amount of spent fuel - " + service.getStatistics().get(0).getProducedFuel());
        System.out.println("Count of broken chips - " + service.getStatistics().get(0).getCountOfBrokenChips());*/

    }
}
