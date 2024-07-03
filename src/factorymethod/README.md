## 팩토리 메소드 패턴

### 1. 해결하고자 하는 것
어떤 인스턴스를 생성하는 책임을 구체적인 클래스가 아닌 추상적인 인터페이스의 메소드로 감싸는 것

### 2. 문제점 
화이트 배를 만든다고 가정하자 사업이 잘 되어 추후에 블랙 쉽을 만들고자 한다

초기에는 화이트쉽만 만들기 때문에 그냥 ShipFactory 클래스가 orderShip이라는 메소드 안에
배를 만드는 과정(로고를 새기기, 완성 알림 주기) 등 모든 처리가 이루어진다

이때 블랙쉽을 만들어야 한다면 if, else 문이 들어가면서 배를 만드는 과정이 갈라진다.
이렇게 되면 추후에 또 다른 배를 만들어야 한다면 처리하는 코드가 추가되면서 코드가 복잡해진다.

이를 해결하고자 추상화를 적용하여 인터페이스안에 기본적인 구현부가 있고 일부 바뀌는 부분들을 추상 메소드로 구분하고
하위 클래스가 해당 기능을 구현하여 구체적인 인스턴스를 만들게 해준다. 

이러한 방식으로 확장에 용이하면서 유연한 설계를 할 수 있다
### Before Code
```java
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
        System.out.println(name + "배 만드는중...");
    }

    private void sendEmail(String email, Ship ship) {
        System.out.println(ship.getName() + "배 완성");
    }
}
```
만약 위 코드에서 또 다른 제품을 만든다고 하면 코드가 계속해서 변경된다.  
이럴 경우 변경에 닫혀있지 않게 된다. 다시 말해 객체지향의 OCP 원칙을 어기게 된다.  

### After Code
```java
public interface ShipFactory {
    
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


public class WhiteShipFactory implements ShipFactory {
    @Override
    public Ship createShip() {
        return new WhiteShip();
    }
}
```
위와 같이 기본적인 로직은 인터페이스에서 default에 구현하며 일부 바뀌어야 하는 부분들은 추상 메소드로 정의하여 하위 클래스들이 
정의하도록 변경

> **defalut 메소드란?**  
자바 8에 추가되었으며 인터페이스에 기본적인 구현을 가능하게 해주는 메소드이다.
(여기는 아직 공부중)

### 장점
기존의 코드를 건드리지 않고 확장이 가능하다. 이러한 이유는 실제 인스턴스를 만드는 부분을 추상 메소드로 변경하여
구체적인 구현은 하위 클래스에게 담당하게 만들어 느슨한 결합을 가능하게 해주기 때문이다.

### 단점
확장에 따라 클래스가 늘어나는 부분이 단점이라고 볼 수 있다. 


### Spring에서는?

```java
BeanFactory xmlFactory = new ClassPathXmlApplicationContext("config.xml");

BeanFactory annotationFactory = new AnnotationConfigApplicationContext(Config.class);
```
스프링에서는 IoC 컨테이너인 BeanFactory를 팩토리 패턴으로 구현해 놓은 것을 알 수 있다.