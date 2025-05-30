package pvz;


import pvz.controller.maincontroller.api.Controller;
import pvz.controller.maincontroller.api.MainController;
import pvz.controller.maincontroller.impl.MainControllerImpl;
import pvz.controller.menucontroller.api.MenuController;

public class PvZ {
    public static void main(String[] args) {
        new MainControllerImpl().start();
    }
}
