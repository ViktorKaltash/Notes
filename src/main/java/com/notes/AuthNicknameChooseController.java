package com.notes;

import com.notes.DB.AccountDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AuthNicknameChooseController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private String email;
    private String password;

    @FXML
    Button registerBTN, abortBTN;
    @FXML
    TextArea nicknameTextArea;
    @FXML
    Text nicknameAlertText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nicknameAlertText.setVisible(false);
    }

    public void initData(String email, String password){
        this.email = email;
        this.password = password;
        System.out.println(email + " " + password);
    }

    public void switchToSceneAuth(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Auth.fxml"));
        root = loader.load();
        AuthController controller = loader.<AuthController>getController();
        controller.initData(email, password);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void registerAccount() throws SQLException {
        if (AccountDB.getUserByEmail(email) != null) {
            System.out.println("Nickname already registered!");
            nicknameAlertText.setText("Nickname already registered!");
            nicknameAlertText.setVisible(true);
            return;
        }
        if (nicknameTextArea.getText().length() < 3) {
            System.out.println("Nickname must be more than 3 characters");
            nicknameAlertText.setText("Nickname must be more than 3 characters");
            nicknameAlertText.setVisible(true);
            return;
        }
        try {
            AccountDB.registerNewUser(nicknameTextArea.getText(), email, password);
            abortBTN.fire();
        } catch (SQLException e) {
            return;
        }
    }
}