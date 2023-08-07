package com.highradius.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String url = "jdbc:mysql://localhost:3306/milestone_db";
    private static final String username = "root";
    private static final String password = "1234";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}






//package com.highradius.connection;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class DatabaseConnection {
//    private static final String url = "jdbc:mysql://localhost:3306/milestone_db";
//    private static final String username = "root";
//    private static final String password = "1234";
//
//    public static Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(url, username, password);
//    }
//}



////package com.highradius.connection;
////
////import java.sql.Connection;
////import java.sql.DriverManager;
////import java.sql.SQLException;
////
////public class DatabaseConnection {
////    private static final String url = "jdbc:mysql://localhost:3306/milestone_two";
////    private static final String username = "root";
////    private static final String password = "Thisarakh2h11126k@";
////
////    public static Connection getConnection() {
////        Connection connection = null;
////        try {
////            connection = DriverManager.getConnection(url, username, password);
////            System.out.println("Database connection successful!");
////        } catch (SQLException e) {
////            System.out.println("Failed to connect to the database.");
////            e.printStackTrace();
////        }
////        return connection;
////    }
////}
//package com.highradius.connection;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.Statement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class DatabaseConnection {
//
//    public static void main(String[] args) {
//
//        String url = "jdbc:mysql://localhost:3306/milestone_two";
//        String username = "root";
//        String password = "Thisarakh2h11126k@";
//
//        try (Connection conn = DriverManager.getConnection(url, username, password)) {
//
//            try (Statement stmt = conn.createStatement();
//                 ResultSet rs = stmt.executeQuery("SELECT AMOUNT_IN_USD FROM h2h_oap")) {
//            	int count = 0;
//
//                while (rs.next()) {
//                    float amount = rs.getFloat("AMOUNT_IN_USD");
//                    System.out.println(amount);
//                    count++;
//                 }
//                System.out.println("count data:" + count);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}





