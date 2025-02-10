package dao;

import entity.Product;

import java.sql.*;

public class Productdao {

    static ResultSet resultSet;
    static int count;
    static Connection connection;

    public static ResultSet fetch() throws SQLException, ClassNotFoundException {
        connection = DbConnect.getConnection();
        String sql = "select * from Product";

        Statement st = connection.createStatement();
        return st.executeQuery(sql);
    }

    public static int insert(Product p) throws SQLException, ClassNotFoundException {
        connection = DbConnect.getConnection();
        String sql = "insert into Product(name, price,quantity) values (?, ?, ?)";

        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, p.getProductName());
        pst.setDouble(2, p.getPrice());
        pst.setInt(3, p.getAvailableQuantity());
        count = pst.executeUpdate();
        return count;
    }

    public static int update(String name, double price, int quantity, int id) throws SQLException, ClassNotFoundException {
        connection = DbConnect.getConnection();
        String sql = "update Product set name = ?, price = ?, quantity = ? where productid = ?";

        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, name);
        pst.setDouble(2, price);
        pst.setInt(3, quantity);
        pst.setInt(4, id);
        count = pst.executeUpdate();
        return count;
    }

    public static int delete(int id) throws SQLException, ClassNotFoundException {
        connection = DbConnect.getConnection();
        String sql = "delete from product where productid = ?";

        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, id);
        count = pst.executeUpdate();

        return count;
    }

}
