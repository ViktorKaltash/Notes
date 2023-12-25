package com.notes;

import com.notes.DB.AccountDB;
import com.notes.DB.NotesDB;
import com.notes.entity.User;
import com.notes.entity.Note;
import com.notes.entity.NoteCasual;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class NotesController implements Initializable {

    private static class NoteButton extends Button {
        private Note note;
        public NoteButton(Note note) {
            super();
            this.note = note;
            this.setText(note.getTextLabel());
            this.setStyle("-fx-background-color: #" + note.getColor()  + "; -fx-text-fill: #" + note.getTextColor() + ";");
            this.setOnAction(this::actionEvent);
        }
        public void actionEvent(ActionEvent actionEvent) {
            curBtn = this;
            System.out.println("curBtn => " + note.getTextLabel());
        }
        public String getNoteText() {
            return note.getText();
        }
        public String getNoteLabel() {
            return note.getTextLabel();
        }
        public void setNoteText(String text) {
            note.setText(text);
        }
        public void setNoteLabel(String label) {
            note.setTextLabel(label);
        }
        public String getNoteModifyDate() {return note.getModifyDate();}
        public void  setNoteModifyDate(String string) {note.setModifyDate(string);}
        public String getCreationDate() {
            return note.getCreationDate();
        }
    };

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ScrollPane scrollPane;
    //grid pane 720W 640H
    @FXML
    private ColorPicker colorPicker, colorPickerText;
    @FXML
    private Button lookInside, addNote, changeNote, delNote;
    @FXML
    private Text symbolsText, dateOfModifyText, dateOfCreationText;
    @FXML
    private TextField noteNameField;
    @FXML
    private Pane colorPreview;
    @FXML
    private GridPane gridPane;
    @FXML
    private Text TextColorLabelPrev;
    private File directory;
    private File[] files;
    public static NoteButton curBtn;
    private Pane[][] panes;
    private User user;

    /*////////////INITIALIZER////////////*/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeStaticParameters();
        initializeUser();
        initializeGridPane();
    }
    private void initializeGridPane() {
        ArrayList<NoteCasual> arrayList = null;
        int receivedNotes;
        try {
            arrayList = NotesDB.getNotesById(user.getId());
            receivedNotes = arrayList.toArray().length;
            receivedNotes--;
        } catch (SQLException e) {
            receivedNotes = 0;
            System.exit(0);
        }
        System.out.println(arrayList.toString());

        gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);

        panes = new StackPane[4][4];

        boolean inArrBounds;
        for (int i = 0; i < panes.length; i++) {
            for (int j = 0; j < panes[i].length; j++) {
                panes[i][j] = new StackPane();
                panes[i][j].setPrefSize(180, 160);
                try {
                    arrayList.get(4*j + i);
                    inArrBounds = true;
                } catch (IndexOutOfBoundsException e) {
                    inArrBounds = false;
                }
                if (receivedNotes >= 0 && inArrBounds) {
                    NoteButton noteButton = new NoteButton(arrayList.get(4*j+i)) {
                        @Override
                        public void actionEvent(ActionEvent actionEvent) {
                            super.actionEvent(actionEvent);
                            getInfoFromCurBtn();
                        }
                    };
                    receivedNotes--;
                    noteButton.setPrefSize(120,120);
                    panes[i][j].getChildren().add(noteButton);
                }
                GridPane.setConstraints(panes[i][j], i, j);
                gridPane.getChildren().add(panes[i][j]);
            }
        }
        gridPane.setStyle("-fx-background-color: #222222; ");
        scrollPane.setContent(gridPane);
    }
    private void initializeUser() {
//        try {
//            user = AccountDB.getUserByNickname("sourceborn");
//            Application.user = user;
//        } catch (SQLException e) {
//            System.out.println("User not found!");
//            System.exit(0);
//        }
        user = Application.user;
    }
    private void initializeStaticParameters() {
        curBtn = null;
    }

    /*///////////////////////////////////*/
    /*/////////////UTILITIES/////////////*/
    /*///////////////////////////////////*/
    public void getInfoFromCurBtn() {
        noteNameField.setText(curBtn.getNoteLabel());
        symbolsText.setText("Symbols: " + curBtn.getNoteText().length() + "");
        dateOfCreationText.setText("Created: " + curBtn.getCreationDate());
        dateOfModifyText.setText("Modified: " + curBtn.getNoteModifyDate() + "");
        colorPreview.setStyle("-fx-background-color: #" + curBtn.note.getColor() + ";");
        TextColorLabelPrev.setStyle("-fx-text-fill: #" + curBtn.note.getTextColor() + ";");
    }
    public void writeToFile(String fileName, String text) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter printWriter;
        String tmpPath = (new File("").getAbsoluteFile() + "\\src\\main\\resources\\Notes\\" + fileName + ".txt");
        Date date = new Date();
        printWriter = new PrintWriter(tmpPath, "UTF-8");
        printWriter.println(colorPicker.getValue());
        printWriter.println(text);
        printWriter.close();
    }
    public void openNote(ActionEvent event, boolean isCreateNote) throws IOException {
        if (curBtn == null && !isCreateNote) {
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("showNote.fxml"));
        root = loader.load();
        ShowNoteController controller = loader.<ShowNoteController>getController();
        if (isCreateNote) {
            controller.initData(isCreateNote);
        } else {
            controller.initData(isCreateNote, curBtn.note);
        }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        isCreateNote = false;
    }
    public void preOpenNote(ActionEvent event) throws IOException {
        boolean isCreateNote;
        if (event.getSource().equals(lookInside)) {
            isCreateNote = false;
            System.out.println(isCreateNote);
        } else {
            isCreateNote = true;
            System.out.println(isCreateNote);
        }
        openNote(event, isCreateNote);
    }
    public void deleteNote() throws SQLException {
        if (curBtn == null) {
            return;
        }
        NotesDB.deleteNote(user.getId(), curBtn.note.getId());
        initializeGridPane();
    }

    public void updateNoteBackgroundColor() throws SQLException {
        if (curBtn == null) {
            return;
        }
        System.out.println("[Note: " + curBtn.getNoteLabel() + "]");
        curBtn.setStyle("-fx-background-color: #" + colorPicker.getValue().toString().substring(2,8) + "; -fx-text-fill: #" + curBtn.note.getTextColor() + ";");
        curBtn.note.setColor(colorPicker.getValue().toString().substring(2,8));
        NotesDB.updateNote(user.getId(), curBtn.note);
    }

    public void updateNoteTextColor() throws SQLException {
        if (curBtn == null) {
            return;
        }
        System.out.println("[Note: " + curBtn.getNoteLabel() + "]");
        curBtn.setStyle("-fx-background-color: #" + curBtn.note.getColor() + "; -fx-text-fill: #" + colorPickerText.getValue().toString().substring(2,8) + ";");
        curBtn.note.setTextColor(colorPickerText.getValue().toString().substring(2,8));
        NotesDB.updateNote(user.getId(), curBtn.note);
    }
    public void updateRows() {

    }
}