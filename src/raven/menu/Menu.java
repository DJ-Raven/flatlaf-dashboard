package raven.menu;

import raven.menu.mode.LightDarkMode;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Raven
 */
public class Menu extends JPanel {

    private final String menuItems[][] = {
        {"~MAIN~"},
        {"Dashboard"},
        {"~WEB APP~"},
        {"Email", "Inbox", "Read", "Compost"},
        {"Chat"},
        {"Calendar"},
        {"~COMPONENT~"},
        {"UI Kit", "Accordion", "Alerts", "Badges", "Breadcrumbs", "Buttons", "Button group"},
        {"Advanced UI", "Cropper", "Owl Carousel", "Sweet Alert"},
        {"Forms", "Basic Elements", "Advanced Elements", "SEditors", "Wizard"},
        {"~OTHER~"},
        {"Charts", "Apex", "Flot", "Peity", "Sparkline"},
        {"Table", "Basic Tables", "Data Table"},
        {"Icons", "Feather Icons", "Flag Icons", "Mdi Icons"},
        {"Special Pages", "Blank page", "Faq", "Invoice", "Profile", "Pricing", "Timeline"}
    };

    public boolean isMenuFull() {
        return menuFull;
    }

    public void setMenuFull(boolean menuFull) {
        this.menuFull = menuFull;
        if (menuFull) {
            header.setText(headerName);
            header.setHorizontalAlignment(JLabel.LEFT);
        } else {
            header.setText("");
            header.setHorizontalAlignment(JLabel.CENTER);
        }
        for (Component com : panelMenu.getComponents()) {
            if (com instanceof MenuItem) {
                ((MenuItem) com).setFull(menuFull);
            }
        }
        lightDarkMode.setMenuFull(menuFull);
    }

    private final List<MenuEvent> events = new ArrayList<>();
    private boolean menuFull = true;
    private final String headerName = "Raven Channel";

    private final boolean hideMenuTitleOnMinimum = true;
    private final int menuTitleLeftInset = 5;
    private final int menuTitleVgap = 5;
    private final int menuMaxWidth = 250;
    private final int menuMinWidth = 60;
    private final int headerFullHgap = 5;

    public Menu() {
        init();
    }

    private void init() {
        setBorder(new EmptyBorder(20, 2, 2, 2));
        setLayout(new MenuLayout());
        putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Menu.background;"
                + "arc:10");

        header = new JLabel(headerName, new ImageIcon(getClass().getResource("/raven/icon/png/logo.png")), JLabel.LEFT);
        header.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$Menu.header.font;"
                + "foreground:$Menu.foreground");

        //  Menu
        scroll = new JScrollPane();
        panelMenu = new JPanel(new MenuItemLayout(this));
        panelMenu.setBorder(new EmptyBorder(5, 5, 5, 5));
        panelMenu.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Menu.background");

        scroll.setViewportView(panelMenu);
        scroll.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:null");
        JScrollBar vscroll = scroll.getVerticalScrollBar();
        vscroll.setUnitIncrement(10);
        vscroll.putClientProperty(FlatClientProperties.STYLE, ""
                + "width:$Menu.scroll.width;"
                + "trackInsets:$Menu.scroll.trackInsets;"
                + "thumbInsets:$Menu.scroll.thumbInsets;"
                + "background:$Menu.ScrollBar.background;"
                + "thumb:$Menu.ScrollBar.thumb");
        createMenu();
        lightDarkMode = new LightDarkMode();
        add(header);
        add(scroll);
        add(lightDarkMode);
    }

    private void createMenu() {
        int index = 0;
        for (int i = 0; i < menuItems.length; i++) {
            String menuName = menuItems[i][0];
            if (menuName.startsWith("~") && menuName.endsWith("~")) {
                panelMenu.add(createTitle(menuName));
            } else {
                MenuItem menuItem = new MenuItem(this, menuItems[i], index++, events);
                panelMenu.add(menuItem);
            }
        }
    }

    private JLabel createTitle(String title) {
        String menuName = title.substring(1, title.length() - 1);
        JLabel lbTitle = new JLabel(menuName);
        lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                + "foreground:$Menu.title.foreground");
        return lbTitle;
    }

    public void addMenuEvent(MenuEvent event) {
        events.add(event);
    }

    public boolean isHideMenuTitleOnMinimum() {
        return hideMenuTitleOnMinimum;
    }

    public int getMenuTitleLeftInset() {
        return menuTitleLeftInset;
    }

    public int getMenuTitleVgap() {
        return menuTitleVgap;
    }

    public int getMenuMaxWidth() {
        return menuMaxWidth;
    }

    public int getMenuMinWidth() {
        return menuMinWidth;
    }

    private JLabel header;
    private JScrollPane scroll;
    private JPanel panelMenu;
    private LightDarkMode lightDarkMode;

    private class MenuLayout implements LayoutManager {

        @Override
        public void addLayoutComponent(String name, Component comp) {
        }

        @Override
        public void removeLayoutComponent(Component comp) {
        }

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                return new Dimension(5, 5);
            }
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                return new Dimension(0, 0);
            }
        }

        @Override
        public void layoutContainer(Container parent) {
            synchronized (parent.getTreeLock()) {
                Insets insets = parent.getInsets();
                int x = insets.left;
                int y = insets.top;
                int gap = 5;
                int width = parent.getWidth() - (insets.left + insets.right);
                int height = parent.getHeight() - (insets.top + insets.bottom);
                int iconWidth = width;
                int iconHeight = header.getPreferredSize().height;
                int hgap = menuFull ? headerFullHgap : 0;
                header.setBounds(x + hgap, y, iconWidth - (hgap * 2), iconHeight);

                int ldgap = 10;
                int ldWidth = width - ldgap * 2;
                int ldHeight = lightDarkMode.getPreferredSize().height;
                int ldx = x + ldgap;
                int ldy = y + height - ldHeight - ldgap;

                int menux = x;
                int menuy = y + iconHeight + gap;
                int menuWidth = width;
                int menuHeight = height - (iconHeight + gap) - (ldHeight + ldgap * 2);
                scroll.setBounds(menux, menuy, menuWidth, menuHeight);

                lightDarkMode.setBounds(ldx, ldy, ldWidth, ldHeight);
            }
        }
    }
}
