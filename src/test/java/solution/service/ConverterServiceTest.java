package solution.service;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testcontainers.containers.PostgreSQLContainer;
import java.io.ByteArrayInputStream;
import java.net.HttpURLConnection;
import java.sql.*;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class ConverterServiceTest {

    private ConverterService converterService;
    private static PostgreSQLContainer<?> container;
    private static Connection connection;

    @Mock
    private HttpURLConnection mockedConnection;

    @BeforeEach
    void setup() throws SQLException {
        MockitoAnnotations.initMocks(this);
        converterService = new ConverterService();
        container = new PostgreSQLContainer<>("postgres:13")
                .withDatabaseName("test")
                .withUsername("test")
                .withPassword("test");
        container.start();
        String jdbcUrl = container.getJdbcUrl();
        String username = container.getUsername();
        String password = container.getPassword();
        connection = DriverManager.getConnection(jdbcUrl, username, password);
        Flyway flyway = Flyway.configure()
                .dataSource(jdbcUrl, username, password)
                .load();
        flyway.migrate();
    }

    @AfterAll
    public static void teardown() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (container != null) {
            container.stop();
        }
    }

    @Test
    public void testDatabaseOperations() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test", "test", "password");
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                assertNotNull(username);
                assertNotNull(email);
                 assertTrue(username.startsWith("user_"));
                 assertTrue(email.contains("@"));
            }
        } finally {
            // Закрытие подключения к базе данных
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Test
    public void testCurrencyApiCom_NonZeroValue() throws Exception {
        String baseCurrency = "USD";
        String targetCurrency = "EUR";
        double amount = 10.0;
        String response = "92";
        when(mockedConnection.getInputStream()).thenReturn(new ByteArrayInputStream(response.getBytes()));
        when(mockedConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_OK);
        String result = converterService.currencyApiCom(baseCurrency, targetCurrency, amount);
        assertNotEquals("0.00", result);
    }
}