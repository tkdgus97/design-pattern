## 퍼사드 패턴(구조 패턴)

### 1. 해결하고자 하는 것
클라이언트가 사용해야 하는 복잡한 서브 시스템 의존성을 간단한 인터페이스로 추상화 하여 높은 의존성을 줄이고자 하는것이 해당 패턴의 목적이라고 볼 수 있다.

### 2. 문제점
아래 메일을 보내는 코드를 보면 메세지를 보내는데 예외나 프로퍼티 등 의존성이 많이 존재한다. 
다시 말해 의존성이 높아져 코드를 변경하기도 어렵고 코드의 변경 가능성도 많아지게 된다.  

### Before Code
```java
public static void main(String[] args) {
    String to = "받는 사람";
    String from = "보내는 사람";
    String host = "127.0.0.1";

    Properties properties = System.getProperties();
    properties.setProperty("mail.smtp.host", host);

    Session session = Session.getDefaultInstance(properties);

    try {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientTyp.TO, new InternetAddress(to));
        message.setSubject("Test mail");

        message.setText("message");
        Transport.send(message);
    } catch (MessagingException e) {
        e.pritStackTrace();
    }
}
```
### After Code

### 장점

### 단점


### Spring에서는?
