package singleton.classload;

public class ClassInit {
    static int v1;
    int v2;

    {
        System.out.println("4. instance init!!");
        System.out.println("5. v1 : " + v1 + " v2 : " + v2);
    }

    static {
        System.out.println("1. v1 === " + v1);
        System.out.println("2. load init!!");
        v1 = 10;
        System.out.println("3. v1 === " + v1);
    }

    public ClassInit() {
        System.out.println("6. call constructor!!");
    }
}
