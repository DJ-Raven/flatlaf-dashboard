# Java Dashboard Light and Dark mode
This dashboard build by using java swing with flatlaf look and feel

### Library use
- flatlaf-3.1.1.jar
- flatlaf-extras-3.1.1.jar
- svgSalamander-1.1.4.jar

### Sample code to show form
``` java
//  Application class from package raven.application
//  Parameter as java.awt.Component

Application.mainForm.showForm(new PanelForm());
```
### Menu
``` java
menu.addMenuEvent(new MenuEvent() {
    @Override
    public void menuSelected(int index, int subIndex) {
        if (index == 1) {
            if (subIndex == 1) {
                Application.mainForm.showForm(new FormInbox());
            } else if (subIndex == 2) {
                Application.mainForm.showForm(new FormRead());
            }
        }
    }
});
```
Modify menu item and submenu item in class [raven.menu.Menu.java](https://github.com/DJ-Raven/java-ui-dashboard-014/blob/003b4b5f49f14280762212c407e496ae43e4a19f/src/raven/menu/Menu.java#L33)</br>
Modify menu event in class [raven.application.form.MainForm.java](https://github.com/DJ-Raven/java-ui-dashboard-014/blob/003b4b5f49f14280762212c407e496ae43e4a19f/src/raven/application/form/MainForm.java#L53)
### Menu item size
Modify menu item height in class [raven.menu.MenuItem.java](https://github.com/DJ-Raven/java-ui-dashboard-014/blob/69bec2044c79a409e98c22e5328934f437c3a040/src/raven/menu/MenuItem.java#L57)</br>
Modify submenu item height in class [raven.menu.MenuItem.java](https://github.com/DJ-Raven/java-ui-dashboard-014/blob/69bec2044c79a409e98c22e5328934f437c3a040/src/raven/menu/MenuItem.java#L58)</br>  

### More custom you can apply flatlaf style properties

- [Flatlaf github](https://github.com/JFormDesigner/FlatLaf)
- [Flatlaf doc](https://www.formdev.com/flatlaf/customizing/)
### Screenshot
![2023-05-21_155517](https://github.com/DJ-Raven/java-ui-dashboard-014/assets/58245926/9ef608d5-8467-47e9-8b9c-5cd3e5c79823)

![2023-05-21_155524](https://github.com/DJ-Raven/java-ui-dashboard-014/assets/58245926/26a633c8-fc1e-4036-88dc-ad3ba1ed6cac)

![2023-05-21_155542](https://github.com/DJ-Raven/java-ui-dashboard-014/assets/58245926/9eebb6a5-f395-405a-a902-b7f1d2e002db)
