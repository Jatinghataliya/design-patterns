package behavioral.visitor.document;

import behavioral.visitor.elements.DocumentElement;
import behavioral.visitor.visitors.DocumentVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Object Structure: Document (Composite / Collection of DocumentElements).
 */
public class Document {
    private final String title;
    private final List<DocumentElement> elements = new ArrayList<>();

    public Document(String title) {
        this.title = title;
    }

    public void addElement(DocumentElement element) {
        elements.add(element);
    }

    /**
     * Accepts a visitor and traverses all child elements.
     */
    public void accept(DocumentVisitor visitor) {
        for (DocumentElement element : elements) {
            element.accept(visitor);
        }
    }

    public String getTitle() { return title; }
}
