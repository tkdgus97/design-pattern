package abstractFactory.before;

import abstractFactory.after.WhiteAnchor;
import abstractFactory.after.WhiteWheel;

public class WhiteShipFactory implements DefaultShipFactory {
    @Override
    public Ship createShip() {
        Ship ship = new WhiteShip();
        ship.setAnchor(new WhiteAnchor());
        ship.setWheel(new WhiteWheel());
        return null;
    }
}
