package behavioral.memento.editor;

/**
 * Originator: TextEditor
 * Produces snapshots of its internal state into Mementos and restores state from Mementos.
 */
public class TextEditor {
    private String content = "";
    private int cursorPosition = 0;
    private String fontStyle = "Regular";
    private int fontSize = 12;

    public void type(String text) {
        if (text != null) {
            this.content += text;
            this.cursorPosition = this.content.length();
        }
    }

    public void setFormat(String fontStyle, int fontSize) {
        this.fontStyle = fontStyle;
        this.fontSize = fontSize;
    }

    public void clear() {
        this.content = "";
        this.cursorPosition = 0;
    }

    /**
     * Saves the current internal state into a new Memento object.
     */
    public EditorState saveState() {
        return new EditorState(this.content, this.cursorPosition, this.fontStyle, this.fontSize);
    }

    /**
     * Restores state from a given Memento object.
     */
    public void restoreState(EditorState memento) {
        if (memento == null) {
            System.out.println("  [Error] Cannot restore null state.");
            return;
        }
        this.content = memento.getContent();
        this.cursorPosition = memento.getCursorPosition();
        this.fontStyle = memento.getFontStyle();
        this.fontSize = memento.getFontSize();
    }

    public String getContent() { return content; }
    public int getCursorPosition() { return cursorPosition; }
    public String getFontStyle() { return fontStyle; }
    public int getFontSize() { return fontSize; }

    @Override
    public String toString() {
        return String.format("TextEditor [Style: %s, Size: %dpt, Cursor: %d] Content: \"%s\"",
                fontStyle, fontSize, cursorPosition, content);
    }
}
