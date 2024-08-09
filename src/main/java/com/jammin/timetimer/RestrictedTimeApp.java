package com.jammin.timetimer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;

public class RestrictedTimeApp extends Application {
    private static final double R = 50;
    private static final double S = R * 2 + 10;

    @Override
    public void start(Stage stage) throws IOException {
        Arc timeArc = createArc();
        Pane arcPane = createArcPane(timeArc);
        TextField timeField = createTimeField(timeArc);

        VBox layout = new VBox(
                10,
                new Label("Enter Timer Length (1-60min)"),
                timeField,
                arcPane
        );
        layout.setPadding(new Insets(10));

        stage.setScene(new Scene(layout));
        stage.show();
    }

    private TextField createTimeField(Arc timeArc) {
        TextField timeField = new TextField();

        TextFormatter<Integer> timeFormatter = new TextFormatter<>(
                new IntegerStringConverter(),
                60,
                this::filter
        );
        timeField.setTextFormatter(
                timeFormatter
        );
        timeField.setOnAction(_ ->
                timeArc.setLength(
                        timeFormatter.getValue() != null
                                ? timeFormatter.getValue() * 6
                                : 0
                )
        );

        return timeField;
    }

    private TextFormatter.Change filter(TextFormatter.Change change) {
        try {
            if (change.getControlNewText().isEmpty()) {
                return change;
            }

            int val = Integer.parseInt(change.getControlNewText());

            return val >= 0 && val <= 60 ? change : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static Arc createArc() {
        Arc timeArc = new Arc(R, R, R, R, 0, 360);
        timeArc.setStroke(Color.MEDIUMSLATEBLUE);
        timeArc.setFill(null);
        timeArc.setStrokeWidth(5);
        timeArc.setStrokeLineCap(StrokeLineCap.ROUND);

        return timeArc;
    }

    private static Pane createArcPane(Arc timeArc) {
        Rectangle background = new Rectangle(S, S, Color.TRANSPARENT);

        return new StackPane(new Group(background, timeArc));
    }
}