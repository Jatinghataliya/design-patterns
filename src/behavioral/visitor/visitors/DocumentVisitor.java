package behavioral.visitor.visitors;

import behavioral.visitor.elements.HeadingElement;
import behavioral.visitor.elements.ImageElement;
import behavioral.visitor.elements.ParagraphElement;
import behavioral.visitor.elements.TableElement;

/**
 * Visitor Interface.
 * Declares visit operations for each concrete element class in the document structure.
 */
public interface DocumentVisitor {
    void visitHeading(HeadingElement heading);
    void visitParagraph(ParagraphElement paragraph);
    void visitImage(ImageElement image);
    void visitTable(TableElement table);
}
