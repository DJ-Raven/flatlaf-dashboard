package raven.menu;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.geom.Path2D;
import java.util.List;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Raven
 */
public class MenuItem extends JPanel {

    public boolean isMenuShow() {
        return menuShow;
    }

    public void setMenuShow(boolean menuShow) {
        this.menuShow = menuShow;
    }

    public float getAnimate() {
        return animate;
    }

    public void setAnimate(float animate) {
        this.animate = animate;
    }

    public String[] getMenus() {
        return menus;
    }

    public int getMenuIndex() {
        return menuIndex;
    }

    private final List<MenuEvent> events;
    private final Menu menu;
    private final String menus[];
    private final int menuIndex;
    private final int menuItemHeight = 38;
    private final int subMenuItemHeight = 35;
    private final int subMenuLeftGap = 34;
    private final int firstGap = 5;
    private boolean menuShow;
    private float animate;

    private PopupSubmenu popup;

    public MenuItem(Menu menu, String menus[], int menuIndex, List<MenuEvent> events) {
        this.menu = menu;
        this.menus = menus;
        this.menuIndex = menuIndex;
        this.events = events;
        init();
    }

    private Icon getIcon() {
        Color lightColor = FlatUIUtils.getUIColor("Menu.icon.lightColor", Color.red);
        Color darkColor = FlatUIUtils.getUIColor("Menu.icon.darkColor", Color.red);
        FlatSVGIcon icon = new FlatSVGIcon("raven/menu/icon/" + menuIndex + ".svg");
        FlatSVGIcon.ColorFilter f = new FlatSVGIcon.ColorFilter();
        f.add(Color.decode("#969696"), lightColor, darkColor);
        icon.setColorFilter(f);
        return icon;
    }

    private void init() {
        setLayout(new MenuLayout());
        putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Menu.background;"
                + "foreground:$Menu.lineColor");
        for (int i = 0; i < menus.length; i++) {
            JButton menuItem = createButtonItem(menus[i]);
            menuItem.setHorizontalAlignment(JButton.LEFT);
            if (i == 0) {
                menuItem.setIcon(getIcon());
                menuItem.addActionListener((ActionEvent e) -> {
                    if (menus.length > 1) {
                        if (menu.isMenuFull()) {
                            MenuAnimation.animate(MenuItem.this, !menuShow);
                        } else {
                            popup.show(menuItem, (int) (menu.getMenuMinWidth() * 0.8f), menuItemHeight / 2);
                        }
                    } else {
                        runEvent(0);
                    }
                });
            } else {
                final int subIndex = i;
                menuItem.addActionListener((ActionEvent e) -> {
                    runEvent(subIndex);
                });
            }
            add(menuItem);
        }
        popup = new PopupSubmenu(menuIndex, menus, events);
    }

    private void runEvent(int subIndex) {
        for (MenuEvent event : events) {
            event.menuSelected(menuIndex, subIndex);
        }
    }

    private JButton createButtonItem(String text) {
        JButton button = new JButton(text);
        button.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Menu.background;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "arc:10;"
                + "iconTextGap:10;"
                + "margin:5,11,5,11");
        return button;
    }

    public void setFull(boolean full) {
        if (full) {
            int size = getComponentCount();
            for (int i = 0; i < size; i++) {
                Component com = getComponent(i);
                if (com instanceof JButton) {
                    JButton button = (JButton) com;
                    button.setText(menus[i]);
                    button.setHorizontalAlignment(JButton.LEFT);
                }
            }
        } else {
            for (Component com : getComponents()) {
                if (com instanceof JButton) {
                    JButton button = (JButton) com;
                    button.setText("");
                    button.setHorizontalAlignment(JButton.CENTER);
                }
            }
            animate = 0f;
            menuShow = false;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (animate > 0) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Path2D.Double p = new Path2D.Double();
            int last = getComponent(getComponentCount() - 1).getY() + (subMenuItemHeight / 2);
            int round = 10;
            int x = subMenuLeftGap - round;
            p.moveTo(x, menuItemHeight + firstGap);
            p.lineTo(x, last - round);
            for (int i = 1; i < getComponentCount(); i++) {
                int com = getComponent(i).getY() + (subMenuItemHeight / 2);
                p.append(createCurve(round, x, com), false);
            }
            g2.setColor(getForeground());
            g2.draw(p);
            g2.dispose();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (menus.length > 1) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
            g2.setColor(FlatUIUtils.getUIColor("Menu.arrowColor", getForeground()));
            if (menu.isMenuFull()) {
                int arrowWidth = 10;
                int arrowHeight = 5;
                int ax = getWidth() - arrowWidth - 10;
                int ay = (menuItemHeight - arrowHeight) / 2;
                Path2D p = new Path2D.Double();
                p.moveTo(0, animate * arrowHeight);
                p.lineTo(arrowWidth / 2, (1f - animate) * arrowHeight);
                p.lineTo(arrowWidth, animate * arrowHeight);
                g2.translate(ax, ay);
                g2.draw(p);
            } else {
                int arrowWidth = 4;
                int arrowHeight = 8;
                int ax = getWidth() - arrowWidth - 3;
                int ay = (menuItemHeight - arrowHeight) / 2;
                Path2D p = new Path2D.Double();
                p.moveTo(0, 0);
                p.lineTo(arrowWidth, arrowHeight / 2);
                p.lineTo(0, arrowHeight);
                g2.translate(ax, ay);
                g2.draw(p);
            }
            g2.dispose();
        }
    }

    private Shape createCurve(int round, int x, int y) {
        Path2D p2 = new Path2D.Double();
        p2.moveTo(x, y - round);
        p2.curveTo(x, y - round, x, y, x + round, y);
        return p2;
    }

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
                Insets inset = parent.getInsets();
                int width = parent.getWidth();
                int height = inset.top + inset.bottom;
                int size = parent.getComponentCount();
                Component item = parent.getComponent(0);
                height += menuItemHeight;
                if (item.isVisible()) {
                    int subMenuHeight = size > 1 ? firstGap : 0;
                    for (int i = 1; i < size; i++) {
                        Component com = parent.getComponent(i);
                        if (com.isVisible()) {
                            subMenuHeight += subMenuItemHeight;
                        }
                    }
                    height += (subMenuHeight * animate);
                } else {
                    height = 0;
                }
                return new Dimension(width, height);
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
                int width = parent.getWidth() - (insets.left + insets.right);
                int size = parent.getComponentCount();
                for (int i = 0; i < size; i++) {
                    Component com = parent.getComponent(i);
                    if (com.isVisible()) {
                        if (i == 0) {
                            com.setBounds(x, y, width, menuItemHeight);
                            y += menuItemHeight + firstGap;
                        } else {
                            com.setBounds(x + subMenuLeftGap, y, width - subMenuLeftGap, subMenuItemHeight);
                            y += subMenuItemHeight;
                        }
                    }
                }
            }
        }
    }
}
