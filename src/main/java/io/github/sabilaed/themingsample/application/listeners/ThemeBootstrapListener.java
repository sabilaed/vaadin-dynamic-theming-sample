package io.github.sabilaed.themingsample.application.listeners;

import org.springframework.stereotype.Component;

import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import com.vaadin.flow.server.communication.IndexHtmlRequestListener;
import com.vaadin.flow.server.communication.IndexHtmlResponse;

import io.github.sabilaed.themingsample.application.util.ThemeUtil;

@Component
public class ThemeBootstrapListener implements VaadinServiceInitListener, IndexHtmlRequestListener {

    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.addIndexHtmlRequestListener(this);
    }

    @Override
    public void modifyIndexHtmlResponse(IndexHtmlResponse indexHtmlResponse) {
        indexHtmlResponse.getDocument().body().attr(
                ThemeUtil.THEME_ATTRIBUTE,
                ThemeUtil.getCurrentThemeVariant().getAttribute());
    }

}
