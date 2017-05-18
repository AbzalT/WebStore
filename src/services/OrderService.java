package services;

import models.OrderBean;

import java.sql.*;
import java.util.UUID;

/**
 * Created by user01 on 17.05.2017.
 */
public class OrderService {
    private Connection connection;
    private Statement statement; //чтобы избежать SQL Injection нужно заменить на PreparedStatement
    private ResultSet resultSet;
    private String url="jdbc:postgresql://localhost:5432/WebStore";
    private String user="postgres";
    private String password="aq1sw2de3";

    public boolean createOrder (OrderBean bean) throws SQLException {
        try {
            Driver driver = new org.postgresql.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(url,user, password);
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO orders VALUES ('"
                    + bean.getId() + "', '"
                    + bean.getUserId() + "', '"
                    + bean.getProductId() + "');");

            return true;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (resultSet!=null) resultSet.close();//он здесь не нужен?
            if (statement!=null) statement.close();
            if (connection!=null) connection.close();
        }

        return false;
    }

    public boolean acceptCheckout(UUID userId){
        try {
            Driver driver = new org.postgresql.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(url,user, password);
            connection.createStatement().executeUpdate("delete FROM public.orders where user_id='" + userId + "';");
            if(statement !=null) statement.close();
            if(connection!=null) connection.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
