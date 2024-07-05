module com.jammin.timetimer {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jammin.timetimer to javafx.fxml;
    exports com.jammin.timetimer;
}