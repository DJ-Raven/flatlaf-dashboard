package raven.application;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLaf;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import raven.application.form.LoginForm;
import raven.application.form.MainForm;

/**
 *
 * @author Raven
 */
public class Application extends javax.swing.JFrame {

    private static Application app;
    private MainForm mainForm;
    private LoginForm loginForm;

    public Application() {
        initComponents();
        setSize(new Dimension(1366, 768));
        setLocationRelativeTo(null);
        mainForm = new MainForm();
        loginForm = new LoginForm();
        setContentPane(loginForm);
    }

    public static void showForm(Component component) {
        app.mainForm.showForm(component);
    }

    public static void login() {
        app.setContentPane(app.mainForm);
        FlatLaf.updateUI();
    }

    public static void logout() {
        app.setContentPane(app.loginForm);
        FlatLaf.updateUI();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 719, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 521, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        FlatLaf.registerCustomDefaultsSource("raven.theme");
        FlatDarculaLaf.setup();
        java.awt.EventQueue.invokeLater(() -> {
            app = new Application();
            app.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
