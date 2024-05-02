package view;

import controller.Controller;

public class View {

    public View(Controller controller) {

        controller.startSale();

        System.out.println("Add 1 item with item id abc123:");
        System.out.println(controller.enterItemID("abc123",1));


        // Flow 1


        // Flow 2


        // Flow 3
        controller.endSale();

        // Flow 4
        //controller.discountRequest();

        // Flow 5
        controller.enterAmount(100);

    }
}
