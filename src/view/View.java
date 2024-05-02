package view;

import controller.Controller;

public class View {

    /**
     * Creates a new instance of the View class.
     * The view class represents the user interface for the cashier.
     * 
     * @param controller The controller for the view to interact with.
     */
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
    }
}
