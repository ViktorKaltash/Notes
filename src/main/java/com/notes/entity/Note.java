package com.notes.entity;

import javafx.scene.paint.Color;

public interface Note {
    public void setId(int id);
    public int getId();
    public String getText();
    public void setText(String text);
    public void setColor(Color color);
    public Color getColor();
    public String getTextLabel();
    public void setTextLabel(String Label);
}