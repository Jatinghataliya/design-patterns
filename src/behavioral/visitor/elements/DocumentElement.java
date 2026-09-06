package behavioral.visitor.elements;

import behavioral.visitor.visitors.DocumentVisitor;

/**
 * Element Interface.
 * Declares the accept method that takes a visitor interface as an argument (Double Dispatch).
 */
public interface DocumentElement {
    void accept(DocumentVisitor visitor);
}
