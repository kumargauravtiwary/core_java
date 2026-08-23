package corejava;

/*
UIThemeFactory
       │
       ├── LightThemeFactory
       │       ├── LightButton
       │       └── LightCheckbox
       │
       └── DarkThemeFactory
               ├── DarkButton
               └── DarkCheckbox
*/
interface Button {

    void render();

    void click();
}
interface Checkbox {

    void render();

    void check();
}
interface UIThemeFactory {

    Button createButton();

    Checkbox createCheckbox();
}
class LightThemeFactory
        implements UIThemeFactory {

    @Override
    public Button createButton() {
        return new LightButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new LightCheckbox();
    }
}
class DarkThemeFactory
        implements UIThemeFactory {

    @Override
    public Button createButton() {
        return new DarkButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new DarkCheckbox();
    }
}

class LightButton implements Button {

    @Override
    public void render() {
        System.out.println(
                "Rendering Light Button"
        );
    }

    @Override
    public void click() {
        System.out.println(
                "Light Button clicked"
        );
    }
}
class LightCheckbox implements Checkbox {

    @Override
    public void render() {
        System.out.println(
                "Rendering Light Checkbox"
        );
    }

    @Override
    public void check() {
        System.out.println(
                "Light Checkbox checked"
        );
    }
}
class DarkButton implements Button {

    @Override
    public void render() {
        System.out.println(
                "Rendering Dark Button"
        );
    }

    @Override
    public void click() {
        System.out.println(
                "Dark Button clicked"
        );
    }
}
class DarkCheckbox implements Checkbox {

    @Override
    public void render() {
        System.out.println(
                "Rendering Dark Checkbox"
        );
    }

    @Override
    public void check() {
        System.out.println(
                "Dark Checkbox checked"
        );
    }
}

public class Application {

    private final Button button;
    private final Checkbox checkbox;

    public Application(UIThemeFactory factory) {

        this.button = factory.createButton();
        this.checkbox = factory.createCheckbox();
    }

    public void render() {

        button.render();
        checkbox.render();
    }

    public void interact() {

        button.click();
        checkbox.check();
    }
}