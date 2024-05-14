module com.vanquyenit.login {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.Login to javafx.fxml;
    exports org.example.Login;
}