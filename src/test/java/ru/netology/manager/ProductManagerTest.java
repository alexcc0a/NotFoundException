package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Phone;
import ru.netology.domain.Product;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ProductManagerTest {
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);
    private Product book1 = new Book(1, "Три товарища", 850, "Эрий Мария Ремарк");
    private Product book2 = new Book(2, "До встречи с тобой", 1150, "Джоджо Мойес");
    private Product book3 = new Book(3, "До встречи с тобой", 1550, "Глилианн Флинн");
    private Product book4 = new Book(4, "Тёмные тайны", 2300, "Глилианн Флинн");
    private Product phone1 = new Phone(11, "iPhone 12", 53_000, "iPhone12");
    private Product phone2 = new Phone(22, "iPhone 13", 115_000, "iPhone13");

    @BeforeEach
    void setUp() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(book4);
        manager.add(phone1);
        manager.add(phone2);
    }

    @Test
    void shouldGetAll() {
        Product[] expected = new Product[]{book1, book2, book3, book4, phone1, phone2};
        Product[] actual = manager.getAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindBookByTitle() {
        Product[] expected = new Product[]{book1};
        Product[] actual = manager.searchBy("Три товарища");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldToFindBooksByTitle() {
        Product[] expected = new Product[]{book2, book3};
        Product[] actual = manager.searchBy("До встречи с тобой");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindSmartphoneByTitle() {
        Product[] expected = new Product[]{phone2};
        Product[] actual = manager.searchBy("iPhone 13");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotFind() {
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("Триллер");
        assertArrayEquals(expected, actual);
    }
}
