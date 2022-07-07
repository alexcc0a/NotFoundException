package ru.netology.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.NotFoundException;
import ru.netology.domain.Phone;
import ru.netology.domain.Product;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ProductRepositoryTest {
    private ProductRepository repository = new ProductRepository();
    private Product book1 = new Book(1, "Три товарища", 850, "Эрий Мария Ремарк");
    private Product book2 = new Book(2, "До встречи с тобой", 1150, "Джоджо Мойес");
    private Product phone1 = new Phone(11, "iPhone 12", 53_000, "iPhone12");
    private Product phone2 = new Phone(22, "iPhone 13", 115_000, "iPhone13");

    @Test
    void shouldThrowAnException() {
        repository.save(book1);
        repository.save(book2);
        repository.save(phone1);
        repository.save(phone2);

        Assertions.assertThrows(NotFoundException.class, () -> repository.removeById(44));
        Product[] expected = new Product[]{book1, book2, phone1, phone2};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldDeletedByIDOneProduct() {
        repository.save(book1);
        repository.save(book2);
        repository.save(phone1);
        repository.save(phone2);

        Product[] expected = new Product[]{book2, phone1, phone2};
        repository.removeById(1);
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldDeletedByIDTwoProduct() {
        repository.save(book1);
        repository.save(book2);
        repository.save(phone1);
        repository.save(phone2);

        Product[] expected = new Product[]{book2, phone1,};
        repository.removeById(1);
        repository.removeById(22);
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldStayEmpty() {
        repository.save(book1);
        repository.save(book2);
        repository.save(phone1);
        repository.save(phone2);

        Product[] expected = new Product[]{};
        repository.removeById(1);
        repository.removeById(2);
        repository.removeById(11);
        repository.removeById(22);
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldAddAllProducts() {
        repository.save(book1);
        repository.save(book2);
        repository.save(phone1);
        repository.save(phone2);

        Product[] expected = new Product[]{book1, book2, phone1, phone2};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotSaveAnything() {
        Product[] expected = new Product[]{};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }
}
