package factorymethod.before;

/**
 * 배 만드는 클래스
 *
 */
public class ShipFactory {
    public Ship orderShip(String name, String email) {
        //validate
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("배 이름을 입력해주세요");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("연락받을 이메일을 남겨주세요");
        }

        prepared(name);

        Ship ship = new Ship();
        ship.setName(name);

        //logo
        if (name.equalsIgnoreCase("whiteship")) {
            ship.setLogo("하얀배 로고");
            ship.setColor("white");
        } else if (name.equalsIgnoreCase("blackship")) {
            ship.setLogo("검은배 로고");
            ship.setColor("black");
        }

        sendEmail(email, ship);
        return ship;
    }

    private void prepared(String name) {
        System.out.println(name + "만드는중...");
    }
    
    private void sendEmail(String email, Ship ship) {
        System.out.println(ship.getName() + "배 완성");
    }
}
