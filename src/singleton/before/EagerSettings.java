package singleton.before;

public class EagerSettings {
    private static EagerSettings instance = new EagerSettings();
    private EagerSettings() {
    }

    public static synchronized EagerSettings getInstance() {
        return instance;
    }
}
