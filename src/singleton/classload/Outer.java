package singleton.classload;

public class Outer {
    int tmp = 1;
    public static Object Holder;
    static String v = ">Outer 클래스 내 static 필드";

    static final String VALUE = ">Outer 클래스의 static final";
    static class Holder {
        static String v2 = ">Inner 클래스 내 static";

        Holder() {
            System.out.println("> Holder Constructor init");
        }
    }

    class Inner {
        static {
            System.out.println(">Inner init 1");
        }
        public Inner() {
            System.out.println("> Inner Init 2");
        }
    }
}
