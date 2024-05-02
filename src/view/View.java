package view;

import controller.Controller;

public class View {

    public View(Controller controller) {

        controller.startSale();

        System.out.println("Add 1 item with item id abc123:");
        System.out.println(controller.enterItemID("abc123",1));

        System.out.println("Add 1 item with item id abc123:");
        System.out.println(controller.enterItemID("abc123",1));

        System.out.println("Add 1 item with item id def456:");
        System.out.println(controller.enterItemID("def456",1));

        System.out.println(controller.endSale());

        System.out.println(controller.enterAmount(100));

        // Flow 4
        //controller.discountRequest();

        // Flow 5


    }
}
