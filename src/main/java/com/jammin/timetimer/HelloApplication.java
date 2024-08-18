package com.jammin.timetimer;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class HelloApplication extends Application {

    @Override
    public void start(Stage PrimaryStage) {
        //Adds all the necessary elements into the window

        StackPane myStackPane = new StackPane();

        VBox myVBox = new VBox(myStackPane);

        Scene myScene = new Scene(myVBox, 400, 400);

        WebView myWebView = createBackgroundImage(myStackPane);

        Arc myArc = createArc(myStackPane);

        myStackPane.getChildren().remove(myArc);

        TextField myTextField = createTextField(myArc);

        myVBox.getChildren().add(myTextField);

        PrimaryStage.setScene(myScene);
        PrimaryStage.show();

    }
    //Constructor for Background Image
    public WebView createBackgroundImage(StackPane myStackPane){
        WebView myWebView = new WebView();
        Double svgWidth = myStackPane.getScene().getWidth();
        Double svgHeight = myStackPane.getScene().getHeight();
        String svgPath = getClass().getResource("/img/timer_ticks.svg").toExternalForm();
        String svgContent = "<html><body><img src = '" + svgPath + "'></body></html>";
        myWebView.getEngine().loadContent(svgContent);
        //myWebView.widthProperty().bind()
        myStackPane.getChildren().add(myWebView);
        return myWebView;
    }

    //Constructor for my Arc
    public Arc createArc(StackPane myStackPane){
        Arc myArc = new Arc(100,100,100,100,0,360);
        myArc.setFill(Color.GREEN);
        myArc.setType(ArcType.ROUND);
        myStackPane.getChildren().add(myArc);

        myArc.radiusXProperty().bind(myArc.getScene().heightProperty().divide(2.5));
        myArc.radiusYProperty().bind(myArc.getScene().heightProperty().divide(2.5));

        return myArc;
    }
    //Textfield to enter the time
    public TextField createTextField(Arc myArc){
        TextField myTextField = new TextField("Enter Time 1-60");

        TextFormatter<Integer> myTextFormatter = new TextFormatter<>(new IntegerStringConverter(), 60, this::filter);

        myTextField.setTextFormatter(myTextFormatter);

        myTextField.setOnAction((evt) -> myArc.setLength(myTextFormatter.getValue()!=null ? 6*myTextFormatter.getValue():0));

        return myTextField;
    }
    //Filter to only allow for use of time between 1-60minutes
    public TextFormatter.Change filter(TextFormatter.Change myChange){
        if (myChange.getControlNewText().isEmpty()){
            return myChange;
        }
        int myVal = Integer.parseInt(myChange.getControlNewText());

        return myVal >=0 && myVal <= 60 ? myChange : null;
    }
}