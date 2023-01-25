package io.github.sabilaed.themingsample.application.views;

import com.vaadin.componentfactory.ToggleButton;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;
import com.vaadin.flow.theme.lumo.LumoUtility.FontSize;

import io.github.sabilaed.themingsample.application.components.appnav.AppNav;
import io.github.sabilaed.themingsample.application.components.appnav.AppNavItem;
import io.github.sabilaed.themingsample.application.enums.ThemeVariant;
import io.github.sabilaed.themingsample.application.util.ThemeUtil;
import io.github.sabilaed.themingsample.application.views.masterdetail.MasterDetailView;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    private H2 viewTitle;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.getElement().setAttribute("aria-label", "Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        H1 appName = new H1("Dynamic Theming Sample");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private AppNav createNavigation() {
        // AppNav is not yet an official component.
        // For documentation, visit https://github.com/vaadin/vcf-nav#readme
        AppNav nav = new AppNav();

        nav.addItem(new AppNavItem("Master-Detail", MasterDetailView.class, "la la-columns"));

        return nav;
    }

    private Footer createFooter() {
        Footer layout = new Footer();

        HorizontalLayout horizontalLayout = new HorizontalLayout();

        createThemeSelector(horizontalLayout);
        layout.add(horizontalLayout);

        return layout;
    }

    private void createThemeSelector(HorizontalLayout horizontalLayout) {
        Icon icon = new Icon(VaadinIcon.MOON_O);
        icon.setSize("1em");

        ToggleButton toggleButton = new ToggleButton("Dark mode");
        if (ThemeUtil.getCurrentThemeVariant().equals(ThemeVariant.Light)) {
            toggleButton.setValue(false);
        } else {
            toggleButton.setValue(true);
        }
        toggleButton.getStyle().set("font-size", "medium");
        toggleButton.getStyle().set("margin-left", "8px");
        toggleButton.addValueChangeListener(value -> {
            if (value.getValue()) {
                ThemeUtil.selectThemeVariant(ThemeVariant.Dark);
            } else {
                ThemeUtil.selectThemeVariant(ThemeVariant.Light);
            }
        });

        horizontalLayout.add(icon, toggleButton);
        horizontalLayout.setAlignItems(Alignment.CENTER);
        horizontalLayout.getStyle().set("margin", "0px 40px");
        horizontalLayout.setPadding(false);
        horizontalLayout.setSpacing(false);
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
