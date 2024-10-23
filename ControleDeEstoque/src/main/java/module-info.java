module org.example.controledeestoque {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.controledeestoque to javafx.fxml;
    exports org.example.controledeestoque;
    exports org.example.controledeestoque.database;
    opens org.example.controledeestoque.database to javafx.fxml;
    exports org.example.controledeestoque.controller;
    opens org.example.controledeestoque.controller to javafx.fxml;
    exports org.example.controledeestoque.model;
    opens org.example.controledeestoque.model to javafx.fxml;
    exports org.example.controledeestoque.dao;
    opens org.example.controledeestoque.dao to javafx.fxml;
}