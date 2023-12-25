package com.notes.entity;

import javafx.scene.paint.Color;

public interface Note {
    public void setId(int id);
    public int getId();
    public String getText();
    public void setText(String text);
    public void setColor(String color);
    public void setTextColor(String color);
    public String getColor();
    public String getTextColor();
    public String getTextLabel();
    public void setTextLabel(String Label);
    public void setModifyDate(String date);
    public String getModifyDate();
    public String getCreationDate();
    public void  setCreationDate(String date);
}