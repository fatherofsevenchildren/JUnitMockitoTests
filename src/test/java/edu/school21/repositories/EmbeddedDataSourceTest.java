package edu.school21.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;



public class EmbeddedDataSourceTest {

    private EmbeddedDatabase db;

    @BeforeEach
    public void init() {
        db = new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .addScript("/schema.sql")
                .addScript("/data.sql")
                .build();
    }

    @Test
    public void testDataAccess() throws SQLException {
        JdbcTemplate template = new JdbcTemplate(db);
        DataSource ds = template.getDataSource();
        assert ds != null;
        assertNotNull(ds.getConnection());
    }

    @AfterEach
    public void tearDown() {
        db.shutdown();
    }
    
}
