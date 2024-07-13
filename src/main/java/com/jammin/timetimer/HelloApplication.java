package com.jammin.timetimer;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Arc;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import javafx.scene.shape.ArcType;
import javafx.scene.paint.Color;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        /*FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();*/

        primaryStage.setTitle("Digital Time Timer");

        //Layout Creation
        VBox myLayout = new VBox();
        Arc timerFace = new Arc();
        TextField timeField = new TextField("Enter Timer Length (1-60min)");
        myLayout.getChildren().add(timerFace);
        myLayout.getChildren().add(timeField);

        //Arc Specifications
        timerFace.setLength(270);
        timerFace.setStartAngle(90);
        timerFace.setRadiusX(100);
        timerFace.setRadiusY(100);
        timerFace.setFill(Color.rgb(29, 69, 40));
        timerFace.setStroke(Color.BLACK);
        timerFace.setStrokeType(StrokeType.INSIDE);
        timerFace.setType(ArcType.ROUND);

        //Text Field Specifications
        timeField.setOnAction(myEvent);

        timeField.setOnAction((evt) -> timerFace.setLength(Double.parseDouble(timeField.getCharacters().toString())*6));

        Scene myScene = new Scene(myLayout);

        primaryStage.setScene(myScene);
        primaryStage.show();
    }

    public class myEvent extends Event {

    }

    public static void main(String[] args) {
        launch();
    }
}