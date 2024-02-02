package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorldCRUD {
    private static WorldCRUD This;

    private static String jdbcUrl = "jdbc:mysql://localhost:3306/world";
    private static String username = "root";
    private static String password = "ensml075f0";

    private static String sqlCreateCityQuery = "INSERT INTO city(Name, CountryCode, District, Population) VALUES(?, ?, ?, ?)";
    private static String sqlTheMostPopularityCityQuery = "SELECT Name FROM country WHERE population > ? AND Code = ?";
    private static String sqlDeleteCityByNameQuery = "DELETE FROM city WHERE Name = ?";

    private Connection connection;

    public static synchronized WorldCRUD getInstance() {
        if (This == null)
            This = new WorldCRUD();
        return This;
    }

    public WorldCRUD init() throws SQLException {
        connection = DriverManager.getConnection(jdbcUrl, username, password);
        return this;
    }

    public void createCity(final String name, final String district) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sqlCreateCityQuery);
        preparedStatement.setString(1, name); // Встановлення значення параметра
        preparedStatement.setString(2, "AFG"); // Встановлення значення параметра
        preparedStatement.setString(3, district); // Встановлення значення параметра
        preparedStatement.setInt(4, 100000); // Встановлення значення параметра

        preparedStatement.executeQuery();
    }

    public List<String> readTheMostPopularityCity() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sqlTheMostPopularityCityQuery);
        preparedStatement.setInt(1, 100000); // Встановлення значення параметра
        preparedStatement.setString(2, "AFG"); // Встановлення значення параметра

        final ArrayList<String> result = new ArrayList<>();

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            result.add(
                    resultSet.getString("name"));
        }

        return result;
    }

    public void updateCityPopulation(final String cityName, final long newCityPopulation) {

    }

    public void deleteCityByName(final String cityName) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sqlDeleteCityByNameQuery);
        preparedStatement.setString(1, cityName); // Встановлення значення параметра

        preparedStatement.executeQuery();
    }
}
