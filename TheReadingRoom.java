import java.util.ArrayList;
import java.util.Scanner;

public class TheReadingRoom {
    private static BookStore bookStore;
    private static ShoppingCart cart;

    public static void main(String[] args) {
        bookStore = new BookStore();
        cart = new ShoppingCart();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("=".repeat(40));
            System.out.println("Welcome to The Reading Room Book Shop!");
            System.out.println("=".repeat(40));
            System.out.println("1. Add a book to your shopping cart");
            System.out.println("2. View shopping cart");
            System.out.println("3. Remove item from shopping cart");
            System.out.println("4. Checkout and pay");
            System.out.println("5. Print list of books");
            System.out.println("6. Quit");
            System.out.println("=".repeat(40));
            System.out.print("Please select: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1 -> {
                    System.out.println("Enter a keyword to search for a book:");
                    String keyword = scanner.nextLine();
                    ArrayList<Book> foundBooks = bookStore.searchBooks(keyword);
                    if (!foundBooks.isEmpty()) {
                        for (int i = 0; i < foundBooks.size(); i++) {
                            System.out.println((i + 1) + ". " + foundBooks.get(i));
                        }
                        System.out.println((foundBooks.size() + 1) + ". Cancel");
                        int bookNumber = scanner.nextInt();
                        scanner.nextLine();
                        if (bookNumber <= foundBooks.size()) {
                            System.out.println("Do you want to buy this as an ebook? (yes/no)");
                            String type = scanner.nextLine();
                            addBookToCart(foundBooks.get(bookNumber - 1), type);
                        } else {
                            System.out.println("Action canceled.");
                        }
                    } else {
                        System.out.println("No books found with that keyword.");
                    }
                }
                case 2 -> cart.viewCart();
                case 3 -> removeItemFromCart(scanner);
                case 4 -> {
                    double total = cart.calculateTotal();
                    System.out.println("You have purchased the items of the total value $" + total);
                    cart.clearCart();
                    System.out.println("Thank you for shopping with The Reading Room!");
                }
                case 5 -> bookStore.printBooks();
                case 6 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addBookToCart(Book book, String type) {
        if (type.equalsIgnoreCase("no")) {
            if (book.getPhysicalCopies() > 0) {
                cart.addPhysicalBook(book);
                System.out.println("Physical copy of " + book.getTitle() + " added to cart.");
            } else {
                System.out.println("Sorry, there are no physical copies of the book.");
            }
        } else if (type.equalsIgnoreCase("yes") && book.hasEbook()) {
            cart.addEbook(book);
            System.out.println("Ebook of " + book.getTitle() + " added to cart.");
        } else {
            System.out.println("Sorry, ebook is not available.");
        }
    }

    private static void removeItemFromCart(Scanner scanner) {
        cart.viewCart();
        System.out.println((cart.getPhysicalBooksSize() + cart.getEbooksSize() + 1) + ". Cancel");
        System.out.print("Select the item to remove from the cart: ");
        int itemNumber = scanner.nextInt();
        scanner.nextLine();

        if (itemNumber > 0 && itemNumber <= cart.getPhysicalBooksSize()) {
            String bookTitle = cart.getPhysicalBookTitle(itemNumber - 1);
            cart.removePhysicalBookByTitle(bookTitle);
            System.out.println("All copies of " + bookTitle + " (Physical) removed from cart.");
        } else if (itemNumber > cart.getPhysicalBooksSize() && itemNumber <= cart.getPhysicalBooksSize() + cart.getEbooksSize()) {
            String ebookTitle = cart.getEbookTitle(itemNumber - cart.getPhysicalBooksSize() - 1);
            cart.removeEbookByTitle(ebookTitle);
            System.out.println("All copies of " + ebookTitle + " (Ebook) removed from cart.");
        } else {
            System.out.println("Action canceled.");
        }
    }
}
