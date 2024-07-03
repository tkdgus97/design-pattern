package factorymethod.after;

public interface ShipFactory {

    /**
     * Java 9 이상이기 때문에 이런식으로 구현이 가능하다 만약 그 이하 버전이라면 따로 기본적인 구현부를 구현하는 abstract 클래스를 만들고 ShipFactory를 상속받은 후
     * 다시 추상 클래스를 하위 클래스들이 상속받아 구체적인 부분을 구현해야 한다.
     * -> private 메소드는 9버전 이후부터 추가되었다.
     */
    default Ship orderShip(String name, String email) {
        validate(name, email);

        prepared(name);

        Ship ship = createShip();

        notify(email, ship);

        return ship;
    }

    private void prepared(String name) {
        System.out.println(name + "만드는 중...");
    }

    Ship createShip();
    private void validate(String name, String email) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("배 이름을 입력해주세요");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("연락받을 이메일을 남겨주세요");
        }
    }

    private void notify(String email, Ship ship) {
        System.out.println(ship.getName() + "배 완성");
    }
}
