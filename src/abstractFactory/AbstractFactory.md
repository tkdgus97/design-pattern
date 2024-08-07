## 추상 팩토리 패턴

### 1. 해결하고자 하는 것
서로 관련있는 여러 객체를 만들어주는 인터페이스를 통해 구체적인 클래스에 의존하지 않으며 객체를 만들고자 하는 것  
해당 패턴의 초점은 클라이언트쪽에 있다 여기서 클라이언트는 객체를 생성하는 팩토리 쪽이라고 볼 수 있다.

### Before Code
```java
public class WhiteShipFactory implements DefaultFactory {
    @Override
    public Ship createShip() {
        Ship ship = new WhiteShip();
        ship.setAnchor(new WhiteAnchor());
        ship.setWheel(new WhiteWheel());
        return null;
    }
}
```
예시를 보면 배를 만드는 팩토리 안에서 WhiteShip을 만드는 팩토리 내부에서 여러 부품을 조립할 때 구체적인 클래스를 조립한다.  
이때 어떤 특정 부품을 바꾸고 싶거나 할 경우 결국 해당 팩토리 코드 안에서 바꾸는 작업이 일어난다.
### After Code
```java
//부품을 만드는 추상 팩토리
public interface ShipPartsFactory {
    Anchor createAnchor();
    Wheel createWheel();
}

//WhiteShip 만들 때 부품 팩토리
public class WhiteShipPartsFactory implements ShipPartsFactory{
    @Override
    public Anchor createAnchor() {
        return new WhiteAnchor();
    }

    @Override
    public Wheel createWheel() {
        return new WhiteWheel();
    }
}

public class WhiteShipFactory implements DefaultShipFactory {
    private final ShipPartsFactory shipPartsFactory;

    public WhiteShipFactory(ShipPartsFactory shipPartsFactory) {
        this.shipPartsFactory = shipPartsFactory;
    }

    @Override
    public Ship createShip() {
        Ship ship = new WhiteShip();
        ship.setAnchor(shipPartsFactory.createAnchor());
        ship.setWheel(shipPartsFactory.createWheel());
        return ship;
    }
}

//쓰는곳
public class Client {
    public static void main(String[] args) {
        DefaultShipFactory shipFactory = new WhiteShipFactory(new WhiteShipPartsFactory());
        Ship ship =  shipFactory.createShip();

        System.out.println(ship.getAnchor().getClass());
        System.out.println(ship.getWheel().getClass());
    }
}

결과
class abstractFactory.after.WhiteAnchor
class abstractFactory.after.WhiteWheel
```
변경된 코드를 보면 기존에 부품을 직접 지정하는 코드가 추상 팩토리에 정의된 createAnchor, createWheel을 통해 생성되는것을 볼 수 있다.  

그렇다면 이렇게 변경할 경우 WhiteShipFactory에 shipFactory로 어떤 구현체를 넘겨주냐에 따라 바뀔 뿐 팩토리 내부에서 직접 부품을 지정하거나 하는 부분은 전혀 변경이 일어나지 않는다.  
좀 더 쉽게 말해 WhiteShipPart 들이 WhiteShipPartPro 부품들로 바뀌어야 한다면 ShipPartsFactory 상속 받는 WhiteShipPartsProFactory를 WhiteShipFactory에 넘겨주기만 하면 된다.  

결론적으로 OCP 원칙을 가지면서도 부품을 생성하는 부분을 ShipPartsFactory로 넘기면서 SRP 원칙을 어느정도 가져갈 수 있다.

### 팩토리 메소드 패턴과 무엇이 다른가?
구체적인 객체 생성 과정을 추상화한 인터페이스로 제공을 한다는 점에서 둘의 모양과 효과는 비슷하다.

하지만 목적과 관점이 다르다  

팩토리 메소드 패턴은 **팩토리를 구현하는 방법**에 초점을 둔다.  
추상 팩토리 패턴은 **팩토리를 사용하는 방법**에 초점을 둔다.

팩토리 메소드 패턴은 객체의 생성 과정을 하위 클래스로 옮기는 것이 목적
추상 팩토리 패턴은 관련 있는 여러 객체 추상화하여 구체적인 클래스에 의존적이지 않게 만드는 것이 목적

### Spring에서는?
