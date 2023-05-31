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
<img src="https://github.com/DJ-Raven/java-ui-dashboard-014/assets/58245926/a3c1aa83-4bd8-4f24-bd96-4ee1e806cd2f" alt="sample 1" width="350"/>
<img src="https://github.com/DJ-Raven/java-ui-dashboard-014/assets/58245926/e9a11a43-c2e1-4b24-bb41-3e8542e3fcf8" alt="sample 1" width="350"/>
</br>
<img src="https://github.com/DJ-Raven/java-ui-dashboard-014/assets/58245926/53799394-90b0-454d-9c0e-2defc8ffd3db" alt="sample 1" width="350"/>
<img src="https://github.com/DJ-Raven/flatlaf-dashboard/assets/58245926/fe793459-33b8-47e7-be06-385c3e4dfa37" alt="sample 1" width="350"/>

### Update Note
- [27-05-2023] Add menu item title use `~` sign around your title name : `{"~YOUR TITLE NAME~"}`
- [28-05-2023] Update auto scale component and change `Application.mainForm.showForm()` to `Application.showForm()`
- [29-05-2023] Update popup submenu item removed border and add drop shadow border
- [31-05-2023] Update add login form
- [31-05-2023] Update selection menu background and add method selected menu by index and subIndex
