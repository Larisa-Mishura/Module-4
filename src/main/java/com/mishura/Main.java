package com.mishura;

import com.mishura.controller.Controller;
import com.mishura.repository.WorkpieceRepository;
import com.mishura.service.Service;

public class Main {
    public static void main(String[] args) {

        final Controller controller = Controller.getInstance();
        String id = controller.create();
        System.out.println(id);

        String id2 = controller.create();
        System.out.println(id2);

        /*String id2 = controller.create();
        System.out.println(id2);*/

    }
}
