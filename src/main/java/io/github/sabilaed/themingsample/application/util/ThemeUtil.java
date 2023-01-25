package io.github.sabilaed.themingsample.application.util;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinSession;

import io.github.sabilaed.themingsample.application.enums.ThemeVariant;

public class ThemeUtil {

    public static final String THEME_ATTRIBUTE = "theme";

    public static void selectThemeVariant(ThemeVariant themeVariant) {
        VaadinSession.getCurrent().setAttribute(THEME_ATTRIBUTE, themeVariant);
        UI ui = UI.getCurrent();
        ui.getElement().setAttribute(THEME_ATTRIBUTE, themeVariant.getAttribute());
    }

    public static ThemeVariant getCurrentThemeVariant() {
        ThemeVariant themeVariant = (ThemeVariant) VaadinSession.getCurrent().getAttribute(THEME_ATTRIBUTE);
        return themeVariant != null ? themeVariant : ThemeVariant.Light;
    }
}