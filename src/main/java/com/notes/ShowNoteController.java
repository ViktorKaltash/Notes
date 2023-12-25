package com.notes;

import com.notes.DB.NotesDB;
import com.notes.entity.Note;
import com.notes.entity.NoteCasual;
import com.notes.entity.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ShowNoteController implements Initializable {

    @FXML
    private TextArea textArea, noteLabel;
    @FXML
    private Button saveBtn, cancelBtn;
    @FXML
    private ColorPicker colorPicker;
    private String ChoosenColor;
    private Stage stage;
    private Scene scene;
    private Parent root;

    private String pathToFile;
    private String nameTmp;

    private boolean isCreateNote;
    private Note note;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ChoosenColor = "ffffff";
    }

    public void initData(boolean isCreateNote, Note note) {
        this.isCreateNote = isCreateNote;
        this.note = note;
        noteLabel.setText(note.getTextLabel());
        textArea.setText(note.getText());
    }

    public void initData(boolean isCreateNote) {
        this.isCreateNote = isCreateNote;
        this.note = new NoteCasual();
        noteLabel.setText(note.getTextLabel());
        textArea.setText(note.getText());
    }

    public void saveData(ActionEvent event) throws SQLException, IOException {
        if (noteLabel.getText().equals("")){
            return;
        }
        if (isCreateNote) {
            registerNoteToDB();
        } else {
            note.setText(textArea.getText());
            updateNoteToDB();
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Notes.fxml"));
        root = loader.load();
        NotesController controller = loader.<NotesController>getController();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void registerNoteToDB() throws SQLException {
        if (noteLabel.getText().equals("")) {
            return;
        }
        note.setText(textArea.getText());
        note.setTextLabel(noteLabel.getText());
        note.setColor(ChoosenColor);
        note.setTextColor("000000");
        NotesDB.registerNewNote(Application.user.getId(), note);
    }

    public void updateNoteToDB() throws SQLException {
        note.setTextLabel(noteLabel.getText());
        note.setText(textArea.getText());
        note.setColor(ChoosenColor);
        NotesDB.updateNote(Application.user.getId(), note);
    }

    public void chooseColor() {
        ChoosenColor = colorPicker.getValue().toString().substring(2,8);
        System.out.println(ChoosenColor);
    }

    public void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Notes.fxml"));
        root = loader.load();
        NotesController controller = loader.<NotesController>getController();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}