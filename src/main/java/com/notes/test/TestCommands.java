package com.notes.test;

import com.notes.DB.AccountDB;
import com.notes.DB.NotesDB;
import com.notes.entity.Note;

import java.sql.SQLException;

public class TestCommands {
    public static void main(String[] args) {
        try {
            AccountDB.getUserByPassword("hx10ascdfb");
            AccountDB.registerNewUser("Noobie2", "NoobPow3r2@gmail.com", "hx11lsdfas");
            System.out.println(NotesDB.getNotesById(1));
            NotesDB.registerNewNote(1, "This is a text from Java programm!", "Java-Test", "", false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}