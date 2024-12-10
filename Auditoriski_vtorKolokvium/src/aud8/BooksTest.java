package aud8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/*
Books collection Problem 5 (0 / 0)
Да се напише класа за книга Book во која се чува:

наслов
категорија
цена.
Да се имплементира конструктор со следните аргументи Book(String title, String category, float price).

Потоа да се напише класа BookCollection во која се чува колекција од книги. Во оваа класа треба да се имплментираат следните методи:

public void addBook(Book book) - додавање книга во колекцијата
public void printByCategory(String category) - ги печати сите книги од проследената категорија (се споредува стрингот без разлика на мали и големи букви), сортирани според насловот на книгата (ако насловот е ист, се сортираат според цената).
public List<Book> getCheapestN(int n) - враќа листа на најевтините N книги (ако има помалку од N книги во колекцијата, ги враќа сите).
 */

class Book {
    String title;
    String category;
    float price;

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public float getPrice() {
        return price;
    }

    public Book(String title, String category, float price) {
        this.title = title;
        this.category = category;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) %.2f", title, category, price);
    }

}

class BookCollection {
    List<Book> books;
//    final Comparator<Book> titleAndPriceComparator = (o1, o2) -> {
//        int res = o1.title.compareTo(o2.title);
//        if (res == 0) {
//            return Float.compare(o1.price, o2.price);
//        } else {
//            return res;
//        }
//    };
//
//    final Comparator<Book> priceComparator = (o1, o2) -> {
//        int res = Float.compare(o1.price, o2.price);
//        if (res == 0) {
//            return o1.title.compareTo(o2.title);
//        } else {
//            return res;
//        }
//    };

    final Comparator<Book> titleAndPriceComparator =
            Comparator.comparing(Book::getTitle)
                    .thenComparing(Book::getPrice);

    final Comparator<Book> priceComparator =
            Comparator.comparing(Book::getPrice)
                    .thenComparing(Book::getTitle);

    public BookCollection() {
        books = new ArrayList<>();
    }


    public void addBook(Book book) {
        books.add(book);
    }

    public void printByCategory(String category) {
        books.stream().
                filter(book -> book.category.equalsIgnoreCase(category))
                .sorted(titleAndPriceComparator)
                .forEach(System.out::println);
    }

    public List<Book> getCheapestN(int n) {
        return books.stream()
                .sorted(priceComparator)
                .limit(n)
                .collect(Collectors.toList());
    }
}

public class BooksTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        BookCollection booksCollection = new BookCollection();
        Set<String> categories = fillCollection(scanner, booksCollection);
        System.out.println("=== PRINT BY CATEGORY ===");
        for (String category : categories) {
            System.out.println("CATEGORY: " + category);
            booksCollection.printByCategory(category);
        }
        System.out.println("=== TOP N BY PRICE ===");
        print(booksCollection.getCheapestN(n));
    }

    static void print(List<Book> books) {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    static TreeSet<String> fillCollection(Scanner scanner,
                                          BookCollection collection) {
        TreeSet<String> categories = new TreeSet<String>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] parts = line.split(":");
            Book book = new Book(parts[0], parts[1], Float.parseFloat(parts[2]));
            collection.addBook(book);
            categories.add(parts[1]);
        }
        return categories;
    }
}


