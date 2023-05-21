# java-ui-dashboard-014
This dashboard build by using java swing with flatlaf look and feel

### Sample code to show form
``` java
//  Application class from package raven.application
//  Parameter as java.awt.Component

Application.mainForm.showForm(new PanelForm());
```
### Menu
``` java
menu.addMenuEvent((int index, int subIndex) -> {
    if (index == 1) {
        if (subIndex == 1) {
            Application.mainForm.showForm(new FormInbox());
        } else if (subIndex == 2) {
            Application.mainForm.showForm(new FormRead());
        }
    }
});
```
You can modify this code in class "raven.application.form.MainForm.java"

### Screenshot
![2023-05-21_155517](https://github.com/DJ-Raven/java-ui-dashboard-014/assets/58245926/467acdf3-b2f0-48d0-a258-ab9637873a63)

![2023-05-21_155524](https://github.com/DJ-Raven/java-ui-dashboard-014/assets/58245926/b38a4fa5-4dd6-45ac-b855-1d8390a54348)

![2023-05-21_155542](https://github.com/DJ-Raven/java-ui-dashboard-014/assets/58245926/d2b72456-5a5d-414b-a0d6-75e57bbd1d48)
