package factorymethod.after;

public class Client {
    public static void main(String[] args) {
        Client c = new Client();
        ShipFactory shipFactory = new WhiteShipFactory();
        Ship whiteShip = shipFactory.orderShip("whiteship", "test");

        Ship blackShip = new BlackShipFactory().orderShip("blackship","test");
    }
}
