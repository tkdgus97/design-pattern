package abstractFactory.before;

import abstractFactory.after.Anchor;
import abstractFactory.after.Wheel;

public class Ship {
    Anchor anchor;

    Wheel wheel;

    public Anchor getAnchor() {
        return anchor;
    }

    public void setAnchor(Anchor anchor) {
        this.anchor = anchor;
    }

    public Wheel getWheel() {
        return wheel;
    }

    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
    }
}
