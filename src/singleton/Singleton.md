## 싱글톤 패턴

### 1. 해결하고자 하는 것
인스턴스를 오직 **한개**만 **제공**
### 2. 문제점
환경 세팅에 대한 정보 등 인스턴스가 오직 하나만 존재해야 하는 경우가 있다. 
### Before Code
```java
Settings s1 = new Settings();
Settings s2 = new Settings();

System.out.println(s1 == s2);
//false
```

위와 같이 생성자를 통해 인스턴스를 만들면 서로 다른 인스턴스라는 것을 알 수 있다.  
싱글톤을 구현하기 위해서는 우선 생성자를 통해 인스턴스를 생성하는 것을 막아야 한다.  
그 후 인스턴스 제공을 위해 글로벌하게 접그할 수 있는 메소드를 통해 인스턴스를 제공한다.

```java
public class Settings {
    private static Settings instance;
    private Settings() {
    }

    public static Settings getInstance() {
        if (instance == null) instance = new Settings();
        return instance;
    }
}
```
```java
Settings s1 = Settings.getInstance();
Settings s2 = Settings.getInstance();

System.out.println(s1 == s2);
//true
```
하지만 우리가 만드는 어플리케이션은 대부분 멀티스레드 환경이다. 즉 여러명이 동시에 요청이 가능하다.  
A 스레드와 B 스레드가 존재할 때 A가 if문을 진입했을 때 B 또한 동시에 if 문을 진입하게 된다면 둘 다 새로운 인스턴스를 만들어 버린다.  
이러한 이유로 Settings의 인스턴스 제공 코드는 스레드 세이프하지 않다.  
### After Code
싱글톤을 스레드 세이프하게 하는 방법은 여러가지가 존재한다. 
1. synchronized 사용
```java
public class SynchSettings {
    private static SynchSettings instance;
    private SynchSettings() {
    }

    public static synchronized SynchSettings getInstance() {
        if (instance == null) {
            instance = new SynchSettings();
        }
        return instance;
    }
}
```
synchronized는 기본적으로 lock을 사용하여 동기화를 시킨다(static 메소드에서의 synchronized 방식은 클래스 단위로 lock이 발생한다)  
이 때 동기화 비용이 클 수 있어 성능에 영향을 미칠 수 있다. 

2. 이른 초기화
```java
public class EagerSettings {
    private static final EagerSettings INSTANCE = new EagerSettings();
    private EagerSettings() {
    }

    public static synchronized EagerSettings getInstance() {
        return INSTANCE;
    }
}
```
만약 나중에 만들 필요가 없다면 미리 만들어 놓을 수도 있다. 
static 인스턴스이기 때문에 클래스 로딩 시에 이미 생성되어 메모리에 올라가게 된다.

3. static inner class  
만약 나중에 생성을 하고 싶다면 static inner class를 활용하는 방법이 있다.  
> 해당 방법이 왜 스레드 세이프 한지와 싱글톤이 가능한지 이해가 잘 안가서 [class load](클래스로드.md)와 관련된 내용을 찾아보게되었다

```java
public class InnerSettings {

    private InnerSettings() {
    }

    private static class InnerSettingsHolder {
        private static final InnerSettings INSTANCE = new InnerSettings();
    }

    public static InnerSettings getInstance() {
        return InnerSettingsHolder.INSTANCE;
    }
}
```
위 세개의 방식은 싱글톤을 구현하는 방식 중 일부이다. 하지만 위의 코드들은 몇가지 방법을 통해 싱글톤을 깨트릴 수 있다.  
그 중 자바의 reflection API를 활용하여 아래처럼 서로 다른 싱글톤 인스턴스를 만들 수 있다.
```java
public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    Constructor<InnerSettings> innerSettings = InnerSettings.class.getDeclaredConstructor();
    innerSettings.setAccessible(true);
    InnerSettings settings = innerSettings.newInstance();

    System.out.println(InnerSettings.getInstance() == settings); //false
}
```

### 장점
- 메모리 절약
- 글로벌 접근성
### 단점
- 멀티스레드 환경에서의 동기화 문제로 인한 데이터 불일치 발생할 수 있음
- 전역 인스턴스이므로 모듈 간 결합도가 높아진다
- private 생성자는 상속이 불가능 하다
  - 위 두 이유로 안티패턴으로 불리기도 한다.
### Java에서는?
자바에서는 대표적으로 Runtime 클래스가 싱글톤으로 구현되어있다.

```java
Runtime runtime = Runtime.getRuntime();


System.out.println(runtime.maxMemory());

//내부 구현
public class Runtime {
    ...
    
    public static Runtime getRuntime() {
        return currentRuntime;
    }

    /** Don't let anyone else instantiate this class */
    private Runtime() {
    }
}
```
