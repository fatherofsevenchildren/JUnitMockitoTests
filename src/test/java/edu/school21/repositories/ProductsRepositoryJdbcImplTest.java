package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ProductsRepositoryJdbcImplTest {

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = new ArrayList<>(Arrays.asList(
            new Product(1L, "milk", 100.0),
            new Product(2L, "beer", 200.0),
            new Product(3L, "coca-cola", 150.0),
            new Product(4L, "merinda", 150.0),
            new Product(5L, "sprite", 140.0)));
    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(1L, "milk", 100.0);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(1L, "new bottel", 500.00);
    final Product BEFORE_UPDATED_PRODUCT = new Product(1L, "milk", 100.00);
    final Product EXPECTED_SAVE_PRODUCT = new Product(6L, "hubabuba", 50.00);


    static EmbeddedDatabase db;
    static ProductsRepositoryJdbcImpl repository;


    @BeforeAll
    public static void init() {
        db = new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .addScript("/schema.sql")
                .addScript("/data.sql")
                .build();
        repository = new ProductsRepositoryJdbcImpl(db);
    }

    @Test
    void findAll() throws SQLException {
        assertEquals(repository.findAll(), EXPECTED_FIND_ALL_PRODUCTS);
    }

    @Test
    void findById() throws SQLException {
        assertEquals(repository.findById(1L).orElseThrow(), EXPECTED_FIND_BY_ID_PRODUCT);
    }

    @Test
    void update() {
        repository.update(EXPECTED_UPDATED_PRODUCT);
        assertEquals(EXPECTED_UPDATED_PRODUCT, repository.findById(1L).orElseThrow());
        repository.update(BEFORE_UPDATED_PRODUCT);
    }

    @Test
    void save() {
        repository.save(EXPECTED_SAVE_PRODUCT);
        assertEquals(EXPECTED_SAVE_PRODUCT, repository.findById(6L).orElseThrow());
    }

    @Test
    void delete() {
        repository.delete(6L);
        assertFalse(repository.findById(6L).isPresent());
    }

    @AfterAll
    public static void closeDs() {
        db.shutdown();
    }

}