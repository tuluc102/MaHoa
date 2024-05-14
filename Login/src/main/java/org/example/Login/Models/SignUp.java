package org.example.Login.Models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignUp {
    private Connection connection;

    public SignUp() {
        connection = DataBase.getInstance().getConnection();
    }

    //Tạo tài khoản
    public boolean createAccountData(String username, String password) {
        // Mã hóa code nhập từ bàn phím
        try {
            // Kiểm tra ký tự đặc biệt trong tên người dùng
            String specialCharsRegex = "[!@#$%^&*(),.?\":{}|<>]";

            if (username.matches(".*" + specialCharsRegex + ".*")) {
                // Hiển thị cảnh báo nếu có ký tự đặc biệt trong tên người dùng
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Thông báo");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Có ký tự đặc biệt");
                successAlert.showAndWait();
                return false;
            }

                // Lưu tài khoản vào bảng user
                String encodedUsername = Encrypt.encoded(username);
                String encodedPassword = Encrypt.encoded(password);

                String userInsertQuery = "INSERT INTO user (username, password) VALUES (?, ?)";
                PreparedStatement userInsertStatement = connection.prepareStatement(userInsertQuery);
                userInsertStatement.setString(1, encodedUsername);
                userInsertStatement.setString(2, encodedPassword);
                userInsertStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //hiển thị màn hình login
    public void showLoginWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/LoginView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("July Cake");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
