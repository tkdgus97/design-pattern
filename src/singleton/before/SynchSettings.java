package singleton.before;

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
