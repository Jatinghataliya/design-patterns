package structural.composite;

import structural.composite.filesystem.FileItem;
import structural.composite.filesystem.FileSystemItem;
import structural.composite.filesystem.FolderItem;

/**
 * Demo runner for Composite Pattern: File System Tree Structure.
 */
public class CompositeDemo {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("          COMPOSITE DESIGN PATTERN DEMO           ");
        System.out.println("       File System Directory Tree Hierarchy       ");
        System.out.println("==================================================\n");

        // 1. Build directory tree:
        // root/
        //   ├── documents/
        //   │     ├── resume.pdf (120 KB)
        //   │     ├── notes.txt (15 KB)
        //   │     └── finance/
        //   │           ├── budget_2025.xlsx (450 KB)
        //   │           └── taxes.pdf (890 KB)
        //   ├── media/
        //   │     ├── background.png (2.5 MB)
        //   │     └── intro.mp4 (48.0 MB)
        //   ├── README.md (4 KB)
        //   └── pom.xml (8 KB)

        FolderItem rootDir = new FolderItem("project_workspace");

        // Documents folder
        FolderItem docsFolder = new FolderItem("documents");
        docsFolder.add(new FileItem("resume.pdf", 120 * 1024));
        docsFolder.add(new FileItem("notes.txt", 15 * 1024));

        // Subfolder: finance
        FolderItem financeFolder = new FolderItem("finance");
        financeFolder.add(new FileItem("budget_2025.xlsx", 450 * 1024));
        financeFolder.add(new FileItem("taxes.pdf", 890 * 1024));
        docsFolder.add(financeFolder);

        // Media folder
        FolderItem mediaFolder = new FolderItem("media");
        mediaFolder.add(new FileItem("background.png", 2621440)); // ~2.5 MB
        mediaFolder.add(new FileItem("intro.mp4", 50331648));    // ~48 MB

        // Add to root
        rootDir.add(docsFolder);
        rootDir.add(mediaFolder);
        rootDir.add(new FileItem("README.md", 4 * 1024));
        rootDir.add(new FileItem("pom.xml", 8 * 1024));

        // 2. Uniform Treatment: Client operates on root folder or subfolder identically
        System.out.println("--- 1. Printing Entire File System Hierarchy ---");
        rootDir.printHierarchy("");

        // 3. Uniform Treatment on a sub-component (Leaf or Sub-Composite)
        System.out.println("\n--- 2. Querying Finance Subtree Directly ---");
        System.out.printf("Folder Name  : %s%n", financeFolder.getName());
        System.out.printf("Total Items  : %d files%n", financeFolder.countItems());
        System.out.printf("Total Size   : %.2f KB%n", financeFolder.getSize() / 1024.0);
        financeFolder.printHierarchy("  ");

        // 4. Querying a single Leaf directly through same interface
        System.out.println("\n--- 3. Querying Single Leaf FileItem through FileSystemItem Interface ---");
        FileSystemItem singleFile = new FileItem("single_config.json", 1024);
        System.out.printf("Item Name    : %s%n", singleFile.getName());
        System.out.printf("Item Count   : %d%n", singleFile.countItems());
        System.out.printf("Item Size    : %d B%n", singleFile.getSize());
        singleFile.printHierarchy("  ");
    }
}
