package behavioral.visitor;

import behavioral.visitor.document.Document;
import behavioral.visitor.elements.HeadingElement;
import behavioral.visitor.elements.ImageElement;
import behavioral.visitor.elements.ParagraphElement;
import behavioral.visitor.elements.TableElement;
import behavioral.visitor.visitors.DocumentStatsVisitor;
import behavioral.visitor.visitors.HtmlExportVisitor;
import behavioral.visitor.visitors.MarkdownExportVisitor;

import java.util.Arrays;

/**
 * Demo runner for Visitor Pattern.
 */
public class VisitorDemo {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("           VISITOR DESIGN PATTERN DEMO            ");
        System.out.println("    Document Export (Markdown, HTML, Analytics)   ");
        System.out.println("==================================================\n");

        // 1. Build a rich Document object structure
        Document doc = new Document("Behavioral Design Patterns Manual");

        doc.addElement(new HeadingElement("Behavioral Design Patterns", 1));
        doc.addElement(new ParagraphElement("Behavioral patterns focus on algorithms and the assignment of responsibilities between objects."));

        doc.addElement(new HeadingElement("Pattern Classification", 2));
        TableElement patternTable = new TableElement(Arrays.asList("Pattern", "Type", "Complexity"));
        patternTable.addRow(Arrays.asList("Strategy", "Behavioral", "Low"));
        patternTable.addRow(Arrays.asList("Observer", "Behavioral", "Medium"));
        patternTable.addRow(Arrays.asList("Visitor", "Behavioral", "High"));
        doc.addElement(patternTable);

        doc.addElement(new HeadingElement("Architecture Diagram", 2));
        doc.addElement(new ImageElement("https://example.com/assets/visitor_uml.png", "Visitor UML Diagram", 800, 450));
        doc.addElement(new ParagraphElement("The above diagram illustrates double dispatch mechanics."));

        // 2. Operation 1: Markdown Export Visitor
        System.out.println("--- 1. Exporting Document to Markdown ---");
        MarkdownExportVisitor markdownVisitor = new MarkdownExportVisitor();
        doc.accept(markdownVisitor);
        System.out.println(markdownVisitor.getExportedMarkdown());

        // 3. Operation 2: HTML Export Visitor
        System.out.println("\n--------------------------------------------------");
        System.out.println("--- 2. Exporting Document to HTML ---");
        HtmlExportVisitor htmlVisitor = new HtmlExportVisitor();
        doc.accept(htmlVisitor);
        System.out.println(htmlVisitor.getExportedHtml());

        // 4. Operation 3: Document Stats / Analytics Visitor
        System.out.println("\n--------------------------------------------------");
        System.out.println("--- 3. Running Document Statistics & Metric Analysis ---");
        DocumentStatsVisitor statsVisitor = new DocumentStatsVisitor();
        doc.accept(statsVisitor);
        statsVisitor.printReport();
    }
}
