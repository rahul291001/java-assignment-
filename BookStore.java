import java.util.ArrayList;

public class BookStore {
    private final ArrayList<Book> books;

    public BookStore() {
        books = new ArrayList<>();
        books.add(new Book("Absolute Java", "Savitch", 5, true));
        books.add(new Book("JAVA: How to Program", "Deitel and Deitel", 0, true));
        books.add(new Book("Computing Concepts with JAVA 8 Essentials", "Horstman", 5, false));
        books.add(new Book("Java Software Solutions", "Lewis and Loftus", 5, false));
        books.add(new Book("Java Program Design", "Cohoon and Davidson", 1, true));
    }

    public ArrayList<Book> searchBooks(String keyword) {
        ArrayList<Book> foundBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    public void printBooks() {
        System.out.println("List of Books:");
        int index = 1;
        for (Book book : books) {
            System.out.println(index + ". " + book);
            index++;
        }
        System.out.println("End of the books");
    }
}    
