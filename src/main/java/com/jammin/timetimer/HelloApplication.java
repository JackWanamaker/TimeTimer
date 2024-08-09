package com.jammin.timetimer;

import javafx.application.Application;
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
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class HelloApplication extends Application {

    @Override
    public void start(Stage PrimaryStage) {
        ImageView myImage = new ImageView(new Image("file:img/kurupt.png"));

        Arc myArc = createArc();

        TextField myTextField = createTextField(myArc);

        StackPane myStackPane = new StackPane(myImage,myArc);

        VBox myVBox = new VBox(myStackPane, myTextField);

        Scene myScene = new Scene(myVBox);

        PrimaryStage.setScene(myScene);
        PrimaryStage.show();

    }

    public Arc createArc(){
        Arc myArc = new Arc(100,100,100,100,0,360);
        myArc.setFill(Color.GREEN);
        myArc.setType(ArcType.ROUND);
        return myArc;
    }

    public TextField createTextField(Arc myArc){
        TextField myTextField = new TextField("Enter Time 1-60");

        TextFormatter<Integer> myTextFormatter = new TextFormatter<>(new IntegerStringConverter(), 60, this::filter);

        myTextField.setTextFormatter(myTextFormatter);

        myTextField.setOnAction((evt) -> myArc.setLength(myTextFormatter.getValue()!=null ? 6*myTextFormatter.getValue():0));

        return myTextField;
    }

    public TextFormatter.Change filter(TextFormatter.Change myChange){
        if (myChange.getControlNewText().isEmpty()){
            return myChange;
        }
        Integer myVal = Integer.parseInt(myChange.getControlNewText());

        return myVal >=0 && myVal <= 60 ? myChange : null;
    }
}