package abstractFactory.after;

import abstractFactory.before.DefaultShipFactory;
import abstractFactory.before.Ship;

public class Client {
    public static void main(String[] args) {
        DefaultShipFactory shipFactory = new WhiteShipFactory(new WhiteShipPartsFactory());
        Ship ship =  shipFactory.createShip();

        System.out.println(ship.getAnchor().getClass());
        System.out.println(ship.getWheel().getClass());
    }
}
