package com.notes.test;

import com.notes.DB.User;

import java.sql.*;

public class TestDB {

    private final static String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "root";

    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            System.out.println("Connected to DataBase " + URL);
            //Выполнить операцию
//            statement.execute("INSERT INTO accounts (account_id, account_name, email, auth_code, reg_Date) VALUES (null, \"New_Acc\", \"New@mail.ru\", \"hxwdfsa1al\", \"2023-12-04\")");
            //Добавить в очередь комманду
//            statement.addBatch("INSERT INTO accounts (account_id, account_name, email, auth_code, reg_Date) VALUES (null, \"New_Acc\", \"New@mail.ru\", \"hxwdfsa1al\", \"2023-12-04\")");
            //Выполнить очередь команд
//            statement.executeBatch();
            //Очистить очередь
//            statement.clearBatch();
            ResultSet resultSet = statement.executeQuery("Select * from accounts where auth_code = \"\"");
            while (resultSet.next()) {
                User user = new User(resultSet.getInt(1));
                user.setAccountName(resultSet.getString(2));
                user.setEmail(resultSet.getString(3));
                user.setDate(resultSet.getString(5));
                System.out.println(user.getId() + "\n" + user.getNickname() + "\n" + user.getEmail() +
                        "\n" + user.getDate());
            }
            resultSet = statement.executeQuery("Select * from accounts");
            System.out.println("==============================");
            User user = null;
            while (resultSet.next()) {
                if (resultSet.getString(4).equals("hx10ascdfb")) {
                    user = new User(resultSet.getInt(1));
                    user.setAccountName(resultSet.getString(2));
                    user.setEmail(resultSet.getString(3));
                    user.setDate(resultSet.getString(5));
                    System.out.println(user.getId() + "\n" + user.getNickname() + "\n" + user.getEmail() +
                            "\n" + user.getDate());
                }
            }
        }
        catch (SQLException e) {
            System.out.println("Connection Failed : " + e.getMessage());
            System.exit(-1);
        }
    }
}