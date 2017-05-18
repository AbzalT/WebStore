package services;

import models.UserBean;

import java.sql.*;
import java.util.UUID;

/**
 * Created by user01 on 17.05.2017.
 */
public class UserService {
    private Connection connection;
    private Statement statement; //чтобы избежать SQL Injection нужно заменить на PreparedStatement
    private ResultSet resultSet;
    private String url="jdbc:postgresql://localhost:5432/WebStore";
    private String user="postgres";
    private String password="aq1sw2de3";
    public String sql;


    public UserBean logIn (UserBean bean) throws SQLException {
        try {
            Driver driver = new org.postgresql.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(url,user,password );
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM users WHERE name='" +
                    bean.getLogin() +
                    "' AND password='" + bean.getPassword() + "'");

            if (resultSet.next())
            {
                return new UserBean(UUID.fromString(resultSet.getString(1)),
                        resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5));
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (resultSet!=null) resultSet.close();
            if (statement!=null) statement.close();
            if (connection!=null) connection.close();
        }

        return null;
    }



    public boolean register (UserBean bean) throws SQLException {
        try {
            Driver driver = new org.postgresql.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(url,user, password);
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO users (id, login, password, name, lastname) VALUES ('"
                    + bean.getId() + "', '"
                    + bean.getLogin() + "', '"
                    + bean.getPassword() + "', '"
                    + bean.getName() + "', '"
                    + bean.getLastName() + "');");

            return true;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (resultSet!=null) resultSet.close();
            if (statement!=null) statement.close();
            if (connection!=null) connection.close();
        }

        return false;
    }
}
