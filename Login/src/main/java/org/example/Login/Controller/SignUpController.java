package org.example.Login.Controller;

import org.example.Login.Models.SignUp;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    public TextField usernametextfield;
    public PasswordField passwordpasswordfield;
    public Button createbutton;
    public PasswordField keypasswordfield;
    public Hyperlink loginlink;
    public CheckBox showpasscheckbox;

    private SignUp createAccount;

    public SignUpController() {
        createAccount = new SignUp();
    }

    public void createButtonAction(ActionEvent event) {
        String username = usernametextfield.getText();
        String password = passwordpasswordfield.getText();
        boolean isValid = createAccount.createAccountData(username, password);

        if (isValid) {
            // Thông báo đăng nhập thành công
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Thông báo");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Tạo tài khoản thành công!");
            successAlert.showAndWait();
        } else {
            // Thông báo đăng nhập thất bại
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Thông báo");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Tạo tài khoản thất bại!");
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
    public void onLogin() {
        SignUp createAccount = new SignUp();
        createAccount.showLoginWindow();
        loginlink.getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginlink.setOnAction(event -> onLogin());
    }
}

