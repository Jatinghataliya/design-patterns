package behavioral.visitor.elements;

import behavioral.visitor.visitors.DocumentVisitor;

/**
 * Concrete Element 3: Image
 */
public class ImageElement implements DocumentElement {
    private final String url;
    private final String altText;
    private final int width;
    private final int height;

    public ImageElement(String url, String altText, int width, int height) {
        this.url = url;
        this.altText = altText;
        this.width = width;
        this.height = height;
    }

    @Override
    public void accept(DocumentVisitor visitor) {
        visitor.visitImage(this);
    }

    public String getUrl() { return url; }
    public String getAltText() { return altText; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
