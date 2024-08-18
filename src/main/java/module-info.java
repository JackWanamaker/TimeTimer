module com.jammin.timetimer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.web;


    opens com.jammin.timetimer to javafx.fxml;
    exports com.jammin.timetimer;
}