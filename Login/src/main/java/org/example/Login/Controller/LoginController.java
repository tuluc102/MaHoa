package org.example.Login.Controller;

import org.example.Login.Models.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {


    public Hyperlink createaccountlink;
    @FXML
    private TextField usernametextfield;

    @FXML
    private PasswordField passwordpasswordfield;

    @FXML
    private Button loginbutton;

    @FXML
    private CheckBox showpasscheckbox;
    private Login login;


    public LoginController() {
        login = new Login();
    }

    public void loginButtonAction(ActionEvent event) throws IOException {
        String username =usernametextfield.getText();
        String password = passwordpasswordfield.getText();

        boolean isValid = login.loginData(username, password);

        if (isValid) {
            // Thông báo đăng nhập thành công
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Thông báo");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Đăng nhập thành công!");
            successAlert.showAndWait();
            loginbutton.getScene().getWindow().hide();


        } else {
            // Thông báo đăng nhập thất bại
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Thông báo");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Đăng nhập thất bại!");
            errorAlert.showAndWait();
        }
    }

    public void handleShowPasswordCheckBoxAction(ActionEvent event) {
        if (showpasscheckbox.isSelected()) {
            passwordpasswordfield.setPromptText(passwordpasswordfield.getText());
            passwordpasswordfield.setText("");
            passwordpasswordfield.setDisable(true);
        } else {
            passwordpasswordfield.setText(passwordpasswordfield.getPromptText());
            passwordpasswordfield.setPromptText("");
            passwordpasswordfield.setDisable(false);
        }
    }

    public void onCreate() {
        Login login = new Login();
        login.showCreateAccountWindow();
        loginbutton.getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createaccountlink.setOnAction(event -> onCreate());
    }
}
