package structural.composite.filesystem;

/**
 * Leaf: File
 * Represents an individual end-element of the tree structure.
 * A Leaf has no children.
 */
public class FileItem implements FileSystemItem {
    private final String name;
    private final long sizeInBytes;
    private final String extension;

    public FileItem(String name, long sizeInBytes) {
        this.name = name;
        this.sizeInBytes = sizeInBytes;
        int dotIndex = name.lastIndexOf('.');
        this.extension = (dotIndex != -1) ? name.substring(dotIndex + 1).toUpperCase() : "BIN";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public long getSize() {
        return sizeInBytes;
    }

    @Override
    public void printHierarchy(String indent) {
        System.out.printf("%s├── 📄 %-24s [%s | %s]%n",
                indent, name, extension, formatSize(sizeInBytes));
    }

    @Override
    public int countItems() {
        return 1;
    }

    private String formatSize(long bytes) {
        if (bytes < 1024) return bytes + " B";
        if (bytes < 1024 * 1024) return String.format("%.1f KB", bytes / 1024.0);
        return String.format("%.2f MB", bytes / (1024.0 * 1024.0));
    }
}
