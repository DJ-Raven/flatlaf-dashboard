package raven.menu;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
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
import java.awt.event.ActionListener;
import java.awt.geom.Path2D;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Raven
 */
public class PopupSubmenu extends JPanel {

    private final List<MenuEvent> events;
    private final int menuIndex;
    private final int subMenuLeftGap = 20;
    private final int subMenuItemHeight = 30;
    private final String menus[];
    private JPopupMenu popup;

    public PopupSubmenu(int menuIndex, String menus[], List<MenuEvent> events) {
        this.menuIndex = menuIndex;
        this.menus = menus;
        this.events = events;
        init();
    }

    private void init() {
        setLayout(new MenuLayout());
        popup = new JPopupMenu();
        setBorder(new EmptyBorder(0, 3, 0, 3));
        popup.add(this);
        popup.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Menu.background");
        putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Menu.background;"
                + "foreground:$Menu.lineColor");
        for (int i = 0; i < menus.length; i++) {
            JButton button = createButtonItem(menus[i]);
            final int subIndex = i;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    runEvent(subIndex);
                    popup.setVisible(false);
                }
            });
            add(button);
        }
    }

    private void runEvent(int subIndex) {
        for (MenuEvent event : events) {
            event.menuSelected(menuIndex, subIndex);
        }
    }

    private JButton createButtonItem(String text) {
        JButton button = new JButton(text);
        button.setHorizontalAlignment(JButton.LEFT);
        button.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Menu.background;"
                + "borderWidth:0;"
                + "arc:10;"
                + "focusWidth:0;"
                + "iconTextGap:10;"
                + "margin:5,11,5,11");
        return button;
    }

    public void show(Component com, int x, int y) {
        popup.show(com, x, y);
        FlatLaf.updateUI();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Path2D.Double p = new Path2D.Double();
        int last = getComponent(getComponentCount() - 1).getY() + (subMenuItemHeight / 2);
        int round = 10;
        int x = subMenuLeftGap - round;
        p.moveTo(x, 0);
        p.lineTo(x, last - round);
        for (int i = 1; i < getComponentCount(); i++) {
            int com = getComponent(i).getY() + (subMenuItemHeight / 2);
            p.append(createCurve(round, x, com), false);
        }
        g2.setColor(getForeground());
        g2.draw(p);
        g2.dispose();
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
                Insets insets = parent.getInsets();
                int x = insets.left;
                int y = insets.top;
                int width = 0;
                int height = (insets.top + insets.bottom);
                int size = parent.getComponentCount();
                for (int i = 1; i < size; i++) {
                    Component com = parent.getComponent(i);
                    if (com.isVisible()) {
                        height += subMenuItemHeight;
                        width = Math.max(width, com.getPreferredSize().width);
                    }
                }
                width += insets.left + insets.right;
                return new Dimension(Math.max(width, 150), height);
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
                int x = insets.left + subMenuLeftGap;
                int y = insets.top;
                int width = parent.getWidth() - (insets.left + insets.right) - subMenuLeftGap;
                int size = parent.getComponentCount();
                for (int i = 1; i < size; i++) {
                    Component com = parent.getComponent(i);
                    if (com.isVisible()) {
                        com.setBounds(x, y, width, subMenuItemHeight);
                        y += subMenuItemHeight;
                    }
                }
            }
        }
    }
}
