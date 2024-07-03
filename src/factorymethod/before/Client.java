package factorymethod.before;

public class Client {
    public static void main(String[] args) {
        Client c = new Client();

        Ship whiteship = new ShipFactory().orderShip("whiteship", "sh@gmail.com");

        Ship blackShip = new ShipFactory().orderShip("blackship","sh@gmail.com");

    }
}
