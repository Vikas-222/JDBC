import dao.Customerdao;
import dao.DbConnect;
import dao.Productdao;
import entity.Customer;
import entity.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection conn = DbConnect.getConnection();
        ResultSet resultSet = null;

        Product p1 = new Product(0, "apple", 70000.00, 5);
        Product p2 = new Product(0, "samsung", 75000.00, 5);
        Product p3 = new Product(0, "redmi", 35000.00, 5);
        Product p4 = new Product(0, "realme", 45000.00, 5);
        Product p5 = new Product(0, "oppo", 80000.00, 5);

        Customer c1 = new Customer(0, "Vikas", "vikas@gmail.com", "123456");
        Customer c2 = new Customer(0, "Arpan", "arpan@gmail.com", "128956");
        Customer c3 = new Customer(0, "Vinay", "vinay@gmail.com", "12345689");
        Customer c4 = new Customer(0, "Krushit", "krushit@gmail.com", "1289786");

        try {
            conn.setAutoCommit(false);

            Productdao.insert(p1);
            Productdao.insert(p2);
            Productdao.insert(p3);
            Productdao.insert(p4);
            Productdao.insert(p5);
            System.out.println("product record inserted");
            System.out.println("");

            Customerdao.insert(c1);
            Customerdao.insert(c2);
            Customerdao.insert(c3);
            Customerdao.insert(c4);
            System.out.println("customer record inserted");
            System.out.println("");

            System.out.println("---------------------Product records------------------------");
            resultSet = Productdao.fetch();
            while (resultSet.next()) {
                System.out.println("ProductId: " + resultSet.getString("productid") + " Product name: " + resultSet.getString("name") +
                        " Price: " + resultSet.getFloat("price") + " Quantity: " + resultSet.getInt("Quantity"));
            }

            System.out.println("");
            System.out.println("---------------------Customer records------------------------");
            resultSet = Customerdao.fetch();
            while (resultSet.next()) {
                System.out.println("CustomerId: " + resultSet.getString("customerid") + " Customer name: " + resultSet.getString("name") +
                        " Email: " + resultSet.getString("email") + " Contact: " + resultSet.getString("phone"));
            }
            System.out.println("");
            System.out.println("------------updating product record id = 4--------------");
            int updatedRows = Productdao.update("Galaxy S2", 50000, 10, 4);
            if (updatedRows != 0) {
                System.out.println("Updation successfull");
            } else
                System.out.println("Updation failed");

            System.out.println("");
            System.out.println("------------Deleting product record id = 3--------------");
            int deletedRows = Productdao.delete(3);
            if (deletedRows != 0) {
                System.out.println("deletion successfull");
            } else
                System.out.println("deletion failed");

            System.out.println("");
            System.out.println("------------updating customer record id = 3--------------");
            int rows = Customerdao.update("Vikas Sahu", "vikas@narola.email", "+91-101010101", 1);
            if (rows != 0) {
                System.out.println("Updation successfull");
            } else
                System.out.println("Updation failed");

            System.out.println("");
            System.out.println("------------Deleting customer record id = 3--------------");
            int delete = Customerdao.delete(3);
            if (delete != 0) {
                System.out.println("deletion successfull");
            } else
                System.out.println("deletion failed");

            conn.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            conn.rollback();
        }

    }


}
