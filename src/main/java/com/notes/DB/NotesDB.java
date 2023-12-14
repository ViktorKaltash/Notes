package com.notes.DB;

import com.notes.entity.Note;
import com.notes.entity.NoteCasual;
import com.notes.entity.Utilities;

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
                arrayList.add(new NoteCasual(resultSet));
            }
            return arrayList;
        } catch (SQLException e) {
            System.out.println("Result: Error");
            return null;
        }
    }

    public static int getNotesAmmount(int id) throws SQLException {
        System.out.println("[Command: Get notes ammount]");
        try {
            ArrayList<NoteCasual> arrayList = new ArrayList<>();
            resultSet = statement.executeQuery("SELECT * FROM " + TableName + " WHERE account_id = \"" + id + "\";");
            while (resultSet.next()) {
                arrayList.add(new NoteCasual(resultSet.getInt(1), resultSet.getString(4), resultSet.getString(3)));
            }
            return arrayList.toArray().length;
        } catch (SQLException e) {
            System.out.println("Result: Error");
            return -1;
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

    public static void registerNewNote(int accountId, Note note) throws SQLException {
        System.out.println("[Command: Register note]");
        try {
            statement.execute("Insert into " + TableName + " (note_id, account_id, note_text, note_label, creation_date, modify_date)\n" +
                    "VALUES (null, " + accountId  + ", \"" + note.getText() + "\", \"" + note.getTextLabel() + "\", \"" +
                    Utilities.getDate() + "\", \"" + Utilities.getDate() + "\");");
            System.out.println("Note registered!");
        } catch (SQLException e) {
            System.out.println("Result: Error");
            throw e;
        }
    }

    public static void updateNote(int userId, Note note) throws SQLException {
        System.out.println("[Command: Update note]");
        try {
            //Update notes set note_text = "Updated text", modify_date = '2023.12.12' where note_id = 1 AND account_id = 1;
            statement.execute("Update notes set " +
                    "note_text = \"" + note.getText() + "\", " +
                    "note_label = \"" + note.getTextLabel() + "\"," +
                    "modify_date = '" + Utilities.getDate() +
                    "' where note_id = " + note.getId() + " AND account_id = " + userId +";");
            System.out.println("Note " + note.getId() + " updated!");
        } catch (SQLException e) {
            System.out.println("Result: Error");
            throw e;
        }
    }

    public static void deleteNote(int userId, int noteId) throws SQLException {
        System.out.println("[Command: Delete note]");
        try {
            //Delete from notes where account_id = userId AND note_id = noteId;
            statement.execute("Delete from notes where account_id = " + userId + " AND note_id = + " + noteId + ";");
            System.out.println("Note " + noteId + " deleted!");
        } catch (SQLException e) {
            System.out.println("Result: Error");
            throw e;
        }
    }
}