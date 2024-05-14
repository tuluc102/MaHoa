package org.example.Login.Models;

import com.vanquyenit.chatapplication.Controller.ChatController;
import com.vanquyenit.chatapplication.Controller.ServerController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.HashMap;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class Login {
    private Connection connection;

    String usename;

    public Login() {
        connection = DataBase.getInstance().getConnection();
    }

    //kiểm tra login
    public boolean loginData( String username,String password) {
        String encodeUsername = Encrypt.encoded(username);
        String encodePassword = Encrypt.encoded(password);
        try {
            // Kiểm tra trong bảng user
            String userQuery = "SELECT * FROM user WHERE username=? AND password=?";
            PreparedStatement userStatement = connection.prepareStatement(userQuery);
            userStatement.setString(1, encodeUsername);
            userStatement.setString(2, encodePassword);
            ResultSet userResultSet = userStatement.executeQuery();

            if (userResultSet.next()) {
                this.usename = username;
                showChatWindow();
                ServerController.receiveMessage(usename+" joined.");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Đăng nhập thất bại
    }
    public void showChatWindow() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/ChatView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            ChatController controller = loader.getController();
            controller.setClientName(usename); // Set the parameter
            loader.setController(controller);
            stage.setScene(new Scene(root));
            stage.setTitle("Chat App");
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.setOnCloseRequest(windowEvent -> {
                controller.shutdown();
            });
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //get màn hình tạo tài khoản
    public void showCreateAccountWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/SignUpView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Chat App");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}