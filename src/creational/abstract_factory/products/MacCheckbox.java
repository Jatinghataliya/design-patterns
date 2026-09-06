package creational.abstract_factory.products;

/**
 * Concrete Product B2: Mac-styled Checkbox
 */
public class MacCheckbox implements Checkbox {
    private boolean checked = false;

    @Override
    public void render() {
        System.out.println("  [Mac Checkbox] Rendering smooth circular toggle with macOS style (checked=" + checked + ").");
    }

    @Override
    public void toggle() {
        this.checked = !this.checked;
        System.out.println("  [Mac Checkbox] Toggled state to: " + checked);
    }
}
