package behavioral.memento.editor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Memento: EditorState
 * Stores an immutable snapshot of the Originator's internal state.
 * Encapsulation rule: State properties are read-only and cannot be altered once created.
 */
public class EditorState {
    private final String content;
    private final int cursorPosition;
    private final String fontStyle;
    private final int fontSize;
    private final String timestamp;

    public EditorState(String content, int cursorPosition, String fontStyle, int fontSize) {
        this.content = content;
        this.cursorPosition = cursorPosition;
        this.fontStyle = fontStyle;
        this.fontSize = fontSize;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS"));
    }

    public String getContent() { return content; }
    public int getCursorPosition() { return cursorPosition; }
    public String getFontStyle() { return fontStyle; }
    public int getFontSize() { return fontSize; }
    public String getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return String.format("Snapshot[@%s] (len=%d, cur=%d, style=%s, size=%dpt) -> \"%s\"",
                timestamp, content.length(), cursorPosition, fontStyle, fontSize, content);
    }
}
