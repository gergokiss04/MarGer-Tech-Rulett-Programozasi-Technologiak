module org.example.rulettjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.rulettjavafx to javafx.fxml;
    exports org.example.rulettjavafx;
}