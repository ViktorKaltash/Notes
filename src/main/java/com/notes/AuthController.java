package com.notes;

import com.notes.DB.AccountDB;
import com.notes.entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AuthController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    Button authorizeBTN, registerBTN;
    @FXML
    TextArea emailTextArea, passwordTextArea;
    @FXML
    Text alertText;
    @FXML
    ImageView authImage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alertText.setVisible(false);
        authImage.setImage(new Image(new File("").getAbsolutePath() + "\\src\\main\\resources\\images\\Notes.png"));
    }

    public void switchToSceneNickname(ActionEvent event) throws IOException, SQLException {
        if (emailTextArea.getText().equals("") || passwordTextArea.getText().equals("")) {
            return;
        }
        if (AccountDB.getUserByEmail(emailTextArea.getText()) != null) {
            System.out.println("Email already registered!");
            alertText.setText("Email already registered!");
            alertText.setVisible(true);
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AuthNicknameChoose.fxml"));
        root = loader.load();
        AuthNicknameChooseController controller = loader.<AuthNicknameChooseController>getController();
        controller.initData(emailTextArea.getText(), passwordTextArea.getText());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSceneNotes(ActionEvent event) throws IOException, SQLException {
        if (emailTextArea.getText().equals("")) {
            alertText.setText("Write down your email!");
            alertText.setVisible(true);
            return;
        }
        if (passwordTextArea.getText().equals("")) {
            alertText.setText("Write down your password!");
            alertText.setVisible(true);
        }
        User user = AccountDB.getUserByEmail(emailTextArea.getText());
        if (user == null) {
            alertText.setText("Your email is not registered!");
            alertText.setVisible(true);
            return;
        }
        System.out.println(passwordTextArea.getText());
        if (!AccountDB.checkPassword(user.getEmail(), passwordTextArea.getText())) {
            alertText.setText("Wrong password!");
            alertText.setVisible(true);
            passwordTextArea.setText("");
            return;
        }

        Application.user = user;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Notes.fxml"));
        root = loader.load();
        NotesController controller = loader.<NotesController>getController();
//        controller.initData(emailTextArea.getText(), passwordTextArea.getText());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void initData(String email, String password) {
        emailTextArea.setText(email);
        passwordTextArea.setText(password);
    }
}