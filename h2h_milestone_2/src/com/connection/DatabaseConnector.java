package com.highradius.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnector {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/milestone_two";
        String username = "root";
        String password = "Thisarakh2h11126k@";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {

            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT AMOUNT_IN_USD FROM h2h_oap")) {
            	int count = 0;

                while (rs.next()) {
                    float amount = rs.getFloat("AMOUNT_IN_USD");
                    System.out.println(amount);
                    count++;
                 }
                System.out.println("count data:" + count);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
