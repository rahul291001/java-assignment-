import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    private final ArrayList<Book> physicalBooks;
    private final ArrayList<Book> ebooks;

    public ShoppingCart() {
        physicalBooks = new ArrayList<>();
        ebooks = new ArrayList<>();
    }

    public void addPhysicalBook(Book book) {
        physicalBooks.add(book);
        book.reducePhysicalCopies();
    }

    public void addEbook(Book book) {
        ebooks.add(book);
    }

    public void removePhysicalBookByTitle(String title) {
        physicalBooks.removeIf(book -> book.getTitle().equals(title));
    }

    public void removeEbookByTitle(String title) {
        ebooks.removeIf(book -> book.getTitle().equals(title));
    }

    public void viewCart() {
        System.out.println("Shopping Cart:");

        Map<String, Integer> physicalBookCount = new HashMap<>();
        Map<String, Integer> ebookCount = new HashMap<>();

        for (Book book : physicalBooks) {
            physicalBookCount.put(book.getTitle(), physicalBookCount.getOrDefault(book.getTitle(), 0) + 1);
        }

        for (Book book : ebooks) {
            ebookCount.put(book.getTitle(), ebookCount.getOrDefault(book.getTitle(), 0) + 1);
        }

        int index = 1;
        for (Map.Entry<String, Integer> entry : physicalBookCount.entrySet()) {
            System.out.println(index + ". " + entry.getKey()+" | " + entry.getValue() +" Copies"+ " | Physical Copy" );
            index++;
        }

        for (Map.Entry<String, Integer> entry : ebookCount.entrySet()) {
            System.out.println(index + ". " + entry.getKey()+" | " + entry.getValue() +" Copies"+ " | Ebook" );
            index++;
        }
    }

    public double calculateTotal() {
        return (physicalBooks.size() * 50.00) + (ebooks.size() * 8.00);
    }

    public void clearCart() {
        physicalBooks.clear();
        ebooks.clear();
    }

    public int getPhysicalBooksSize() {
        return physicalBooks.size();
    }

    public int getEbooksSize() {
        return ebooks.size();
    }

    public String getPhysicalBookTitle(int index) {
        return physicalBooks.get(index).getTitle();
    }

    public String getEbookTitle(int index) {
        return ebooks.get(index).getTitle();
    }
}
