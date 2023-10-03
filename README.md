# Java Dashboard Light and Dark mode
This dashboard build by using java swing with flatlaf look and feel

### Library use
- flatlaf-3.2.jar
- flatlaf-extras-3.2.jar
- jsvg-1.2.0.jar
- flatlaf-fonts-roboto-2.137.jar
- swing-toast-notifications-1.0.1.jar

### Sample code to show form
``` java
//  Application class from package raven.application
//  Parameter as java.awt.Component

Application.showForm(new PanelForm());

//  Set menu selection by index and subIndex

Application.setSelectedMenu(0, 0);
```
### Menu Items
``` java
//  Modify this code in raven.menu.Menu.java

private final String menuItems[][] = {
    {"~MAIN~"}, //  Menu title
    {"Dashboard"},
    {"Email", "Inbox", "Read", "Compost"},
};
```
### Menu Event
``` java
menu.addMenuEvent(new MenuEvent() {
    @Override
    public void menuSelected(int index, int subIndex, MenuAction action) {
        if (index == 1) {
            if (subIndex == 1) {
                Application.showForm(new FormInbox());
            } else if (subIndex == 2) {
                Application.showForm(new FormRead());
            }
        } else {
            action.cancel();
        }
    }
});
```

### More custom you can apply flatlaf style properties

- [Flatlaf github](https://github.com/JFormDesigner/FlatLaf)
- [Flatlaf doc](https://www.formdev.com/flatlaf/customizing/)
### Screenshot
<img src="https://github.com/DJ-Raven/flatlaf-dashboard/assets/58245926/23ab0477-c11e-498d-92f9-37f6bfa944f6" alt="sample 1" width="350"/>
<img src="https://github.com/DJ-Raven/flatlaf-dashboard/assets/58245926/44d1972b-b29b-4a11-8fdd-be7f27782e5b" alt="sample 1" width="350"/>
</br>
<img src="https://github.com/DJ-Raven/flatlaf-dashboard/assets/58245926/71c03d69-4508-43ea-86e6-2cba0c8e1dc8" alt="sample 1" width="350"/>
<img src="https://github.com/DJ-Raven/flatlaf-dashboard/assets/58245926/fe793459-33b8-47e7-be06-385c3e4dfa37" alt="sample 1" width="350"/>

### Update Note
- [27-05-2023] Add menu item title use `~` sign around your title name : `{"~YOUR TITLE NAME~"}`
- [28-05-2023] Update auto scale component and change `Application.mainForm.showForm()` to `Application.showForm()`
- [29-05-2023] Update popup submenu item removed border and add drop shadow border
- [31-05-2023] Update add login form
- [31-05-2023] Update selection menu background and add method selected menu by index and subIndex
- [17-06-2023] Update add [Toast Notifications](https://github.com/DJ-Raven/swing-toast-notifications.git)
- [27-06-2023] Update add menu font properties for menu item and menu label `Menu.item.font` and `Menu.label.font`
- [27-06-2023] Update menu support right to left by enable this [code](https://github.com/DJ-Raven/flatlaf-dashboard/blob/70d08d66fa48f72e55ae873cbc2968e4ac151b57/src/raven/application/Application.java#L87C13-L87C13)
- [03-10-2023] Update add properties `AccentControl.show` to show and hide accent color toolbar
