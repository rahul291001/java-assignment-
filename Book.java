public class Book {
    private final String title;
    private final String authors;
    private int physicalCopies;
    private final boolean hasEbook;

    public Book(String title, String authors, int physicalCopies, boolean hasEbook) {
        this.title = title;
        this.authors = authors;
        this.physicalCopies = physicalCopies;
        this.hasEbook = hasEbook;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return authors;
    }

    public int getPhysicalCopies() {
        return physicalCopies;
    }

    public boolean hasEbook() {
        return hasEbook;
    }

    public void increasePhysicalCopies() {
        physicalCopies++;
    }

    public void reducePhysicalCopies() {
        if (physicalCopies > 0) {
            physicalCopies--;
        }
    }

    @Override
    public String toString() {
        return title + " | " + authors + " | " + physicalCopies + " | " + (hasEbook ? "Yes" : "No");
    }
}
