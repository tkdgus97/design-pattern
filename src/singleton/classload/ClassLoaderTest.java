package singleton.classload;

import java.lang.reflect.InvocationTargetException;

public class ClassLoaderTest {
    public static void main(String[] args)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        System.out.println("1.load!!!!!!!!!!");
        Class<? extends Outer> outerClass = Outer.class;

        System.out.println("===============================================");

        System.out.println("init!!!!!!!");
        Outer outer=outerClass.getDeclaredConstructor().newInstance();
        System.out.println(outer.tmp);
    }
}
