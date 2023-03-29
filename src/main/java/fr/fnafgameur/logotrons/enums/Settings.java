package fr.fnafgameur.logotrons.enums;

import java.util.function.Supplier;

public enum Settings {

    // Settings
    TEST("Test","Test", null);

    private final String name;
    private final String description;
    private final Supplier<Settings> settingsSupplier;

    Settings(String name, String description, Supplier<Settings> settingsSupplier) {
        this.name = name;
        this.description = description;
        this.settingsSupplier = settingsSupplier;
    }

    public String getName() {
        return name;
    }
    public String getDescription(){
        return description;
    }
    public Settings createInstance(){
        return settingsSupplier.get();
    }
}
