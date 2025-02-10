package dao;

import entity.Customer;
import entity.Product;

import java.sql.*;

public class Customerdao {

    static ResultSet resultSet;
    static Connection connection;
//    private static Product p;

    public static ResultSet fetch() throws SQLException, ClassNotFoundException {
        connection = DbConnect.getConnection();
        String sql = "select * from Customer";

        Statement st = connection.createStatement();
        return st.executeQuery(sql);
    }

    public static int insert(Customer c) throws SQLException, ClassNotFoundException {
        connection = DbConnect.getConnection();
        String sql = "insert into Customer(name, email,phone) values (?, ?, ?)";

        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1,c.getCustomerName());
        pst.setString(2,c.getEmail());
        pst.setString(3,c.getPhone());
        return pst.executeUpdate();
    }

    public static int update(String name,String email,String phone,int cid) throws SQLException, ClassNotFoundException {
        connection = DbConnect.getConnection();
        String sql = "update Customer set name = ?, email = ?, phone = ? where customerid = ?";

        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1,name);
        pst.setString(2,email);
        pst.setString(3,phone);
        pst.setInt(4,cid);
        return pst.executeUpdate();
    }

    public static int delete(int id) throws SQLException, ClassNotFoundException {
        connection = DbConnect.getConnection();
        String sql = "delete from Customer where customerid = ?";

        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1,id);
        return pst.executeUpdate();
    }
}
