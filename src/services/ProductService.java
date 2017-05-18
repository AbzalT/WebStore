package services;

import models.ProductBean;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by user01 on 17.05.2017.
 */
public class ProductService {
    private Connection connection;
    private Statement statement; //чтобы избежать SQL Injection нужно заменить на PreparedStatement
    private ResultSet resultSet;
    private String url="jdbc:postgresql://localhost:5432/WebStore";
    private String user="postgres";
    private String password="aq1sw2de3";

    public ResultSet getProducts() throws SQLException {
        try {
            Driver driver = new org.postgresql.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(url,user, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM products");
            return resultSet;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (resultSet!=null) resultSet.close();
            if (statement!=null) statement.close();
            if (connection!=null) connection.close();
        }

        return null;
    }

    public boolean putAll (ArrayList<ProductBean> products) throws SQLException {
        try {
            Driver driver = new org.postgresql.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(url,user, password);
            connection.createStatement().executeUpdate("delete FROM public.products;");//зачем все удалять?
            PreparedStatement prepStmt = connection.prepareStatement(
                    "insert into products (id, name, description, imageaddress, price) values (?,?,?,?,?);");
            for (ProductBean product : products) {
                prepStmt.setObject(1, product.getId());
                prepStmt.setString(2, product.getName());
                prepStmt.setString(3, product.getDescription());
                prepStmt.setString(4, product.getImageAddress());
                prepStmt.setInt(5, product.getPrice());
                prepStmt.addBatch();

            }
            int [] numUpdates=prepStmt.executeBatch();
            for (int i=0; i < numUpdates.length; i++) {
                if (numUpdates[i] == -2)
                    return true;
                else
                    return true;
            }

            return false;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (resultSet!=null) resultSet.close();//resultSet -? не используется
            if (statement!=null) statement.close();
            if (connection!=null) connection.close();
        }

        return false;
    }
}
