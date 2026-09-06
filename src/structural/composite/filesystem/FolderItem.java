package structural.composite.filesystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Composite: Folder / Directory.
 * Represents a container that can store child FileSystemItems (both Leaf files and sub-folders).
 * Implements operations by delegating and aggregating results from its children.
 */
public class FolderItem implements FileSystemItem {
    private final String name;
    private final List<FileSystemItem> children = new ArrayList<>();

    public FolderItem(String name) {
        this.name = name;
    }

    public void add(FileSystemItem item) {
        if (item != null && !children.contains(item)) {
            children.add(item);
        }
    }

    public void remove(FileSystemItem item) {
        children.remove(item);
    }

    public List<FileSystemItem> getChildren() {
        return Collections.unmodifiableList(children);
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * Composite calculation: recursively sums the size of all contained files & subfolders.
     */
    @Override
    public long getSize() {
        long totalSize = 0;
        for (FileSystemItem child : children) {
            totalSize += child.getSize();
        }
        return totalSize;
    }

    @Override
    public void printHierarchy(String indent) {
        System.out.printf("%s📁 %-24s [Folder | %s | %d items]%n",
                indent, name + "/", formatSize(getSize()), countItems());
        for (FileSystemItem child : children) {
            child.printHierarchy(indent + "   ");
        }
    }

    /**
     * Composite calculation: recursively counts all individual items within this directory.
     */
    @Override
    public int countItems() {
        int total = 0;
        for (FileSystemItem child : children) {
            total += child.countItems();
        }
        return total;
    }

    private String formatSize(long bytes) {
        if (bytes < 1024) return bytes + " B";
        if (bytes < 1024 * 1024) return String.format("%.1f KB", bytes / 1024.0);
        return String.format("%.2f MB", bytes / (1024.0 * 1024.0));
    }
}
