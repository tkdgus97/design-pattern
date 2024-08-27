package singleton.before;

public class InnerSettings {

    private static class InnerSettingsHolder {
        private static final InnerSettings INSTANCE = new InnerSettings();
    }

    public static InnerSettings getInstance() {
        return InnerSettingsHolder.INSTANCE;
    }
}
