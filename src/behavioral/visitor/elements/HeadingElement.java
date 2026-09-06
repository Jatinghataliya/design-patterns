package behavioral.visitor.elements;

import behavioral.visitor.visitors.DocumentVisitor;

/**
 * Concrete Element 1: Heading
 */
public class HeadingElement implements DocumentElement {
    private final String text;
    private final int level; // 1 = H1, 2 = H2, etc.

    public HeadingElement(String text, int level) {
        this.text = text;
        this.level = Math.max(1, Math.min(6, level));
    }

    @Override
    public void accept(DocumentVisitor visitor) {
        // Double dispatch: passing itself (HeadingElement) to visitor
        visitor.visitHeading(this);
    }

    public String getText() { return text; }
    public int getLevel() { return level; }
}
