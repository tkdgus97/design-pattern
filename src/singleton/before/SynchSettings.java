package singleton.before;

public class SynchSettings {
    private static SynchSettings instance;
    private SynchSettings() {
    }
    static {
        System.out.println("class");
    }

    public static synchronized SynchSettings getInstance() {
        if (instance == null) {
            instance = new SynchSettings();
        }
        return instance;
    }
}
