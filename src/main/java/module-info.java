module com.senai.javafx.brasabrasil {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.senai.javafx.brasabrasil to javafx.fxml;
    exports com.senai.javafx.brasabrasil;
}