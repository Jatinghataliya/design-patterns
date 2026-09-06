package creational.abstract_factory.products;

/**
 * Concrete Product B1: Windows-styled Checkbox
 */
public class WindowsCheckbox implements Checkbox {
    private boolean checked = false;

    @Override
    public void render() {
        System.out.println("  [Windows Checkbox] Rendering square box with checkmark (checked=" + checked + ").");
    }

    @Override
    public void toggle() {
        this.checked = !this.checked;
        System.out.println("  [Windows Checkbox] Toggled state to: " + checked);
    }
}
