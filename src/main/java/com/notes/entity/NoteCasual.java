package com.notes.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NoteCasual implements Note {
    private int id;
    private String noteText;
    private String noteLabel;
    private String noteColor;
    private String modifyDate;
    private String creationDate;
    private String textColor;
    public NoteCasual(int id, String noteLabel, String noteText) {
        this.id = id;
        this.noteLabel = noteLabel;
        this.noteText = noteText;
    }

    public NoteCasual() {
        noteLabel = "";
        noteText = "";
    }
    public NoteCasual(ResultSet resultSet) throws SQLException {
        id = resultSet.getInt(1);
        noteText = resultSet.getString(3);
        noteLabel = resultSet.getString(4);
        creationDate = resultSet.getString(5);
        modifyDate = resultSet.getString(6);
        if (resultSet.getString(7).equals("null")) {
            noteColor = "000000";
        } else {
            noteColor = resultSet.getString(7);
        }
        if (resultSet.getString(8) == null) {
            textColor = "ffffff";
        } else {
            textColor = resultSet.getString(8);
        }
    }
    @Override
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public int getId() {
        return id;
    }
    @Override
    public String getText() {
        return noteText;
    }
    @Override
    public void setText(String text) {
        this.noteText = text;
    }
    @Override
    public void setColor(String color) {
        noteColor = color;
    }

    @Override
    public void setTextColor(String color) {
        textColor = color;
    }

    @Override
    public String getColor() {
        return noteColor;
    }

    @Override
    public String getTextColor() {
        return textColor;
    }

    @Override
    public String getTextLabel() {
        return noteLabel;
    }

    @Override
    public void setTextLabel(String label) {
        noteLabel = label;
    }

    @Override
    public void setModifyDate(String date) {
        modifyDate = date;
    }

    @Override
    public String getModifyDate() {
        return modifyDate;
    }

    @Override
    public String getCreationDate() {
        return creationDate;
    }

    @Override
    public void setCreationDate(String date) {
        creationDate = date;
    }

    @Override
    public String toString() {
        return ("id: " + id + "\ntext: " + noteText + "\nlabel: " + noteLabel);
    }
}