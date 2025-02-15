package ies.pedro.datalayer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DataBaseConnectionTest {
    private DataBaseConnection dataBaseConnection;
    @BeforeEach
    void setUp() {
        this.dataBaseConnection = new DataBaseConnection();

    }

    @AfterEach
    void tearDown() {
        try {
            this.dataBaseConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void testOpenConnection() {
        this.dataBaseConnection.setConfig_path("/properties/config.properties");
        try {
            this.dataBaseConnection.open();
            assertEquals(this.dataBaseConnection.isOpen(), true);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



}