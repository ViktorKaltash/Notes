module com.example.notes {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.notes to javafx.fxml;
    exports com.notes;
    exports com.notes.test;
    opens com.notes.test to javafx.fxml;
}