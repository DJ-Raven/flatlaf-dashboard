package raven.application.form;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import raven.application.Application;
import raven.application.form.other.FormInbox;
import raven.application.form.other.FormRead;
import raven.menu.Menu;

/**
 *
 * @author Raven
 */
public class MainForm extends JLayeredPane {

    public MainForm() {
        init();
    }

    private void init() {
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new MainFormLayout());
        menu = new Menu();
        panelBody = new JPanel(new BorderLayout());
        menuButton = new JButton(new FlatSVGIcon("raven/icon/svg/menu_left.svg", 0.8f));
        menuButton.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;"
                + "focusWidth:0;"
                + "borderWidth:0");
        menuButton.addActionListener((ActionEvent e) -> {
            setMenuFull(!menu.isMenuFull());
        });
        initMenuEvent();
        setLayer(menuButton, JLayeredPane.POPUP_LAYER);
        add(menuButton);
        add(menu);
        add(panelBody);
    }

    private void initMenuEvent() {
        menu.addMenuEvent((int index, int subIndex) -> {
            // Application.mainForm.showForm(new DefaultForm("Form : " + index + " " + subIndex));
            if (index == 1) {
                if (subIndex == 1) {
                    Application.mainForm.showForm(new FormInbox());
                } else if (subIndex == 2) {
                    Application.mainForm.showForm(new FormRead());
                }
            }
        });
    }

    private void setMenuFull(boolean full) {
        String icon = full ? "menu_left.svg" : "menu_right.svg";
        menuButton.setIcon(new FlatSVGIcon("raven/icon/svg/" + icon, 0.8f));
        menu.setMenuFull(full);
        revalidate();
    }

    public void showForm(Component component) {
        panelBody.removeAll();
        panelBody.add(component);
        panelBody.repaint();
        panelBody.revalidate();
    }

    private Menu menu;
    private JPanel panelBody;
    private JButton menuButton;

    private class MainFormLayout implements LayoutManager {

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
                int width = parent.getWidth() - (insets.left + insets.right);
                int height = parent.getHeight() - (insets.top + insets.bottom);
                int menuWidth = menu.isMenuFull() ? menu.getMenuMaxWidth() : menu.getMenuMinWidth();
                menu.setBounds(x, y, menuWidth, height);

                int menuButtonWidth = menuButton.getPreferredSize().width;
                int menuButtonHeight = menuButton.getPreferredSize().height;
                int menuX = (int) (x + menuWidth - (menuButtonWidth * (menu.isMenuFull() ? 0.5f : 0.3f)));
                menuButton.setBounds(menuX, 30, menuButtonWidth, menuButtonHeight);

                int gap = 5;
                int bodyWidth = width - menuWidth - gap;
                int bodyHeight = height;
                int bodyx = x + menuWidth + gap;
                int bodyy = y;
                panelBody.setBounds(bodyx, bodyy, bodyWidth, bodyHeight);
            }
        }
    }
}
