package com.notes.DB;

import com.notes.entity.User;
import com.notes.entity.Utilities;

import java.sql.*;

public class AccountDB {
    private final static String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "root";
    private final static String TableName = "accounts";
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    static {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Connection Failed : " + e.getMessage() + " Going to offline mode");
            System.exit(-1);    //На самом деле оффлайн режим
        }
    }

    public static User getUserByPassword(String password) throws SQLException {
        System.out.println("[Command: Get user]");
        try {
            resultSet = statement.executeQuery("SELECT * FROM " + TableName + " WHERE auth_code = \"" + password + "\";");
            resultSet.next();
            User user = new User(resultSet.getInt(1));
            user.setAccountName(resultSet.getString(2));
            user.setEmail(resultSet.getString(3));
            user.setDate(resultSet.getString(5));
            System.out.println("Result: \nUser: " + user.getNickname() +
                    "\nEmail: " + user.getEmail() + "\nRegistration date: " + user.getDate());
            return user;
        } catch (SQLException e) {
            System.out.println("Result: Error");
            return null;
        }
    }

    public static User getUserByEmail(String email) throws SQLException {
        System.out.println("[Command: Get user by email]");
        try {
            resultSet = statement.executeQuery("SELECT * FROM " + TableName + " WHERE email = \"" + email + "\";");
            resultSet.next();
            User user = new User(resultSet.getInt(1));
            user.setAccountName(resultSet.getString(2));
            user.setEmail(resultSet.getString(3));
            user.setDate(resultSet.getString(5));
            System.out.println("Result: \nUser: " + user.getNickname() +
                    "\nEmail: " + user.getEmail() + "\nRegistration date: " + user.getDate());
            return user;
        } catch (SQLException e) {
            System.out.println("Result: Error");
            return null;
        }
    }

    public static User getUserByNickname(String nickname) throws SQLException {
        System.out.println("[Command: Get user by email]");
        try {
            resultSet = statement.executeQuery("SELECT * FROM " + TableName + " WHERE account_name = \"" + nickname + "\";");
            resultSet.next();
            User user = new User(resultSet.getInt(1));
            user.setAccountName(resultSet.getString(2));
            user.setEmail(resultSet.getString(3));
            user.setDate(resultSet.getString(5));
            System.out.println("Result: \nUser: " + user.getNickname() +
                    "\nEmail: " + user.getEmail() + "\nRegistration date: " + user.getDate());
            return user;
        } catch (SQLException e) {
            System.out.println("Result: Error");
            return null;
        }
    }

    public static void registerNewUser(String nickname, String email, String password) throws SQLException {
        System.out.println("[Command: Register user]");
        try {
            statement.execute("Insert into " + TableName + " (account_id, account_name, email, auth_code, reg_date)\n" +
                    "VALUES (null, \"" + nickname + "\", \"" + email + "\", \"" + password + "\", \"" + Utilities.getDate() + "\");");
            System.out.println("User registered!");
        } catch (SQLException e) {
            System.out.println("Result: Error");
            throw e;
        }
    }

    public static boolean checkPassword(String email, String password) throws SQLException {
        System.out.println("[Command: Check user password]");
        try {
            resultSet = statement.executeQuery("SELECT * FROM " + TableName + " WHERE email = \"" + email + "\";");
            resultSet.next();
            return resultSet.getString(4).equals(password);
        } catch (SQLException e) {
            System.out.println("Result: Error");
            throw e;
        }
    }
}
