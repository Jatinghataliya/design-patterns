package behavioral.visitor.visitors;

import behavioral.visitor.elements.HeadingElement;
import behavioral.visitor.elements.ImageElement;
import behavioral.visitor.elements.ParagraphElement;
import behavioral.visitor.elements.TableElement;

import java.util.List;

/**
 * Concrete Visitor 1: Markdown Export Visitor.
 * Exports the document structure into standard Markdown format.
 */
public class MarkdownExportVisitor implements DocumentVisitor {
    private final StringBuilder markdown = new StringBuilder();

    @Override
    public void visitHeading(HeadingElement heading) {
        String hashes = "#".repeat(heading.getLevel());
        markdown.append(hashes).append(" ").append(heading.getText()).append("\n\n");
    }

    @Override
    public void visitParagraph(ParagraphElement paragraph) {
        markdown.append(paragraph.getContent()).append("\n\n");
    }

    @Override
    public void visitImage(ImageElement image) {
        markdown.append("![").append(image.getAltText()).append("](")
                .append(image.getUrl()).append(")\n\n");
    }

    @Override
    public void visitTable(TableElement table) {
        List<String> headers = table.getHeaders();
        markdown.append("| ").append(String.join(" | ", headers)).append(" |\n");

        // Separator
        markdown.append("|");
        for (int i = 0; i < headers.size(); i++) {
            markdown.append(" --- |");
        }
        markdown.append("\n");

        // Rows
        for (List<String> row : table.getRows()) {
            markdown.append("| ").append(String.join(" | ", row)).append(" |\n");
        }
        markdown.append("\n");
    }

    public String getExportedMarkdown() {
        return markdown.toString().trim();
    }
}
