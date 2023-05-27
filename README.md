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
### Menu Items
``` java
//  Modify this code in raven.menu.Menu.java

private final String menuItems[][] = {
    {"~MAIN~"}, //  Menu title
    {"Dashboard"},
    {"Email", "Inbox", "Read", "Compost"},
};
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

### More custom you can apply flatlaf style properties

- [Flatlaf github](https://github.com/JFormDesigner/FlatLaf)
- [Flatlaf doc](https://www.formdev.com/flatlaf/customizing/)
### Screenshot
<img src="https://github.com/DJ-Raven/java-ui-dashboard-014/assets/58245926/9ef608d5-8467-47e9-8b9c-5cd3e5c79823" alt="sample 1" width="250"/>
<img src="https://github.com/DJ-Raven/java-ui-dashboard-014/assets/58245926/26a633c8-fc1e-4036-88dc-ad3ba1ed6cac" alt="sample 1" width="250"/>
<img src="https://github.com/DJ-Raven/java-ui-dashboard-014/assets/58245926/9eebb6a5-f395-405a-a902-b7f1d2e002db" alt="sample 1" width="250"/>
</br></br>
- New update [26-05-2023]
</br></br>
<img src="https://github.com/DJ-Raven/java-ui-dashboard-014/assets/58245926/68e6026b-8993-4ddc-a4be-c4d33681b580" alt="sample 1" width="250"/>

### Update Note
- [27-05-2023] Add menu item title use `~` sign around your title name : `{"~YOUR TITLE NAME~"}`

