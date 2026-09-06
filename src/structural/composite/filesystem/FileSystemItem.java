package structural.composite.filesystem;

/**
 * Component Interface.
 * Declares common operations for both simple leaf items (File)
 * and complex compound containers (Folder/Directory).
 */
public interface FileSystemItem {
    String getName();
    long getSize(); // Size in bytes
    void printHierarchy(String indent);
    int countItems();
}
