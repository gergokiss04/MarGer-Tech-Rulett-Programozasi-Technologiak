module org.example.rulettjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.rulettjavafx to javafx.fxml;
    exports org.example.rulettjavafx;
}