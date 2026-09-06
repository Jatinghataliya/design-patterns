package behavioral.visitor.visitors;

import behavioral.visitor.elements.HeadingElement;
import behavioral.visitor.elements.ImageElement;
import behavioral.visitor.elements.ParagraphElement;
import behavioral.visitor.elements.TableElement;

import java.util.List;

/**
 * Concrete Visitor 2: HTML Export Visitor.
 * Exports the document structure into semantic HTML tags.
 */
public class HtmlExportVisitor implements DocumentVisitor {
    private final StringBuilder html = new StringBuilder();

    @Override
    public void visitHeading(HeadingElement heading) {
        html.append("<h").append(heading.getLevel()).append(">")
                .append(escapeHtml(heading.getText()))
                .append("</h").append(heading.getLevel()).append(">\n");
    }

    @Override
    public void visitParagraph(ParagraphElement paragraph) {
        html.append("<p>").append(escapeHtml(paragraph.getContent())).append("</p>\n");
    }

    @Override
    public void visitImage(ImageElement image) {
        html.append("<img src=\"").append(image.getUrl())
                .append("\" alt=\"").append(escapeHtml(image.getAltText()))
                .append("\" width=\"").append(image.getWidth())
                .append("\" height=\"").append(image.getHeight())
                .append("\" />\n");
    }

    @Override
    public void visitTable(TableElement table) {
        html.append("<table border=\"1\">\n  <thead>\n    <tr>\n");
        for (String header : table.getHeaders()) {
            html.append("      <th>").append(escapeHtml(header)).append("</th>\n");
        }
        html.append("    </tr>\n  </thead>\n  <tbody>\n");

        for (List<String> row : table.getRows()) {
            html.append("    <tr>\n");
            for (String cell : row) {
                html.append("      <td>").append(escapeHtml(cell)).append("</td>\n");
            }
            html.append("    </tr>\n");
        }
        html.append("  </tbody>\n</table>\n");
    }

    private String escapeHtml(String input) {
        if (input == null) return "";
        return input.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;");
    }

    public String getExportedHtml() {
        return html.toString().trim();
    }
}
