package behavioral.visitor.visitors;

import behavioral.visitor.elements.HeadingElement;
import behavioral.visitor.elements.ImageElement;
import behavioral.visitor.elements.ParagraphElement;
import behavioral.visitor.elements.TableElement;

import java.util.List;

/**
 * Concrete Visitor 3: Document Metrics & Word Count Visitor.
 * Calculates statistics (word counts, image count, table cells) without altering element classes.
 */
public class DocumentStatsVisitor implements DocumentVisitor {
    private int headingCount = 0;
    private int paragraphCount = 0;
    private int imageCount = 0;
    private int tableCount = 0;
    private int totalWordCount = 0;

    @Override
    public void visitHeading(HeadingElement heading) {
        headingCount++;
        totalWordCount += countWords(heading.getText());
    }

    @Override
    public void visitParagraph(ParagraphElement paragraph) {
        paragraphCount++;
        totalWordCount += countWords(paragraph.getContent());
    }

    @Override
    public void visitImage(ImageElement image) {
        imageCount++;
        totalWordCount += countWords(image.getAltText());
    }

    @Override
    public void visitTable(TableElement table) {
        tableCount++;
        for (String header : table.getHeaders()) {
            totalWordCount += countWords(header);
        }
        for (List<String> row : table.getRows()) {
            for (String cell : row) {
                totalWordCount += countWords(cell);
            }
        }
    }

    private int countWords(String text) {
        if (text == null || text.trim().isEmpty()) return 0;
        return text.trim().split("\\s+").length;
    }

    public void printReport() {
        System.out.println("--- Document Structure & Statistics Analysis ---");
        System.out.printf("  - Headings   : %d%n", headingCount);
        System.out.printf("  - Paragraphs : %d%n", paragraphCount);
        System.out.printf("  - Images     : %d%n", imageCount);
        System.out.printf("  - Tables     : %d%n", tableCount);
        System.out.printf("  - Total Words: %d words%n", totalWordCount);
    }
}
