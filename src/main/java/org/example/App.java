package org.example;

import org.flywaydb.core.Flyway;

import java.sql.SQLException;

/**
 * Hello world!
 */
public class App {
    private static String jdbcUrl = "jdbc:mysql://localhost:3306/demo";
    private static String username = "root";
    private static String password = "ensml075f0";

    public static void main(String[] args) throws SQLException {
        Flyway.configure()
                .dataSource(jdbcUrl, username, password)
                .locations("classpath:flyway/scripts")
                .load()
                .migrate();

        final WorldCRUD crud = WorldCRUD.getInstance()
                .init();

        for (String city : crud.readTheMostPopularityCity())
            System.out.println(city);
    }
}
