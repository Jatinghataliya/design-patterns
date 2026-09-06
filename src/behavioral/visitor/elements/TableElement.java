package behavioral.visitor.elements;

import behavioral.visitor.visitors.DocumentVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete Element 4: Table
 */
public class TableElement implements DocumentElement {
    private final List<String> headers;
    private final List<List<String>> rows = new ArrayList<>();

    public TableElement(List<String> headers) {
        this.headers = new ArrayList<>(headers);
    }

    public void addRow(List<String> rowData) {
        rows.add(new ArrayList<>(rowData));
    }

    @Override
    public void accept(DocumentVisitor visitor) {
        visitor.visitTable(this);
    }

    public List<String> getHeaders() { return headers; }
    public List<List<String>> getRows() { return rows; }
}
