package behavioral.visitor.elements;

import behavioral.visitor.visitors.DocumentVisitor;

/**
 * Concrete Element 2: Paragraph
 */
public class ParagraphElement implements DocumentElement {
    private final String content;

    public ParagraphElement(String content) {
        this.content = content;
    }

    @Override
    public void accept(DocumentVisitor visitor) {
        visitor.visitParagraph(this);
    }

    public String getContent() { return content; }
}
