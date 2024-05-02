package view;

import controller.Controller;

public class View {

    public View(Controller controller) {

        // Flow 1
        controller.startSale();

        // Flow 2
        controller.enterItemID("abc123",1);

        // Flow 3
        controller.endSale();

        // Flow 4
        //controller.discountRequest();

        // Flow 5
        controller.enterAmount(100);

    }
}
