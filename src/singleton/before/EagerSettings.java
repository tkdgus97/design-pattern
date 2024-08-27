package singleton.before;

public class EagerSettings {
    private static final EagerSettings INSTANCE = new EagerSettings();
    private EagerSettings() {
    }

    public static synchronized EagerSettings getInstance() {
        return INSTANCE;
    }
}
