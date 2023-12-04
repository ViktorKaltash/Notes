package com.notes.DB;

import com.notes.entity.NoteCasual;

import java.sql.*;
import java.util.ArrayList;

public class NotesDB {
    private final static String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "root";
    private final static String TableName = "notes";
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

    public static ArrayList<NoteCasual> getNotesById(int id) throws SQLException {
        System.out.println("[Command: Get notes]");
        try {
            ArrayList<NoteCasual> arrayList = new ArrayList<>();
            resultSet = statement.executeQuery("SELECT * FROM " + TableName + " WHERE account_id = \"" + id + "\";");
            while (resultSet.next()) {
                arrayList.add(new NoteCasual(resultSet.getInt(1), resultSet.getString(4), resultSet.getString(3)));
            }
            return arrayList;
        } catch (SQLException e) {
            System.out.println("Result: Error");
            return null;
        }
    }

    public static void registerNewNote(int accountId, String text,  String label, String Color, boolean isImportant) throws SQLException {
        System.out.println("[Command: Register note]");
        try {
            statement.execute("Insert into " + TableName + " (note_id, account_id, note_text, note_label)\n" +
                    "VALUES (null, " + accountId  + ", \"" + text + "\", \"" + label + "\");");
            System.out.println("Note registered!");
        } catch (SQLException e) {
            System.out.println("Result: Error");
            throw e;
        }
    }
}
