package io.github.sabilaed.themingsample.application.enums;

public enum ThemeVariant {
    Light("Standard"), Dark("Dark");

    private final String caption;

    ThemeVariant(String caption) {
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }

    public String getAttribute() {
        return name().toLowerCase();
    }
}
