module com.example.chessfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.xml.dom;


    opens com.example.chessfx to javafx.fxml;
    exports com.example.chessfx;
    exports com.example.chessfx.pieces;
    opens com.example.chessfx.pieces to javafx.fxml;
}