package com.notes.entity;
import javafx.scene.paint.Color;

public class NoteCasual implements Note {
    private int id;
    private String noteText;
    private String noteLabel;
    private Color noteColor;
    public NoteCasual(int id, String noteLabel, String noteText) {
        this.id = id;
        this.noteLabel = noteLabel;
        this.noteText = noteText;
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
    public void setColor(Color color) {
        noteColor = color;
    }
    @Override
    public Color getColor() {
        return noteColor;
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
    public String toString() {
        return ("id: " + id + "\ntext: " + noteText + "\nlabel: " + noteLabel);
    }
}