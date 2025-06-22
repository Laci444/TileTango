package com.unideb.inf.tiletango.frontend;

import javafx.beans.property.DoubleProperty;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class TileView extends StackPane {
    int number;
    Circle circle;
    Text text;

    public TileView(int number) {
        this.number = number;

        circle = new Circle();

        text = new Text();
        text.setMouseTransparent(true); // prevent text consuming mouse events
        text.setText(number < 0 ? null : String.valueOf(number)); // middle tile don't render number

        String circleType = number < 0 ? "center-tile" : "circle-tile";
        circle.getStyleClass().addAll(circleType, "tile");

        this.layoutXProperty().bind(this.centerXProperty().subtract(this.radiusProperty()));
        this.layoutYProperty().bind(this.centerYProperty().subtract(this.radiusProperty()));
        this.prefWidthProperty().bind(this.radiusProperty().multiply(2));
        this.prefHeightProperty().bind(this.radiusProperty().multiply(2));

        this.setPickOnBounds(false); // prevent invisible areas registering mouse events

        this.getChildren().addAll(circle, text);
    }

    public int getNumber() {
        return number;
    }

    public DoubleProperty radiusProperty() {
        return circle.radiusProperty();
    }

    public DoubleProperty centerXProperty() {
        return circle.centerXProperty();
    }

    public DoubleProperty centerYProperty() {
        return circle.centerYProperty();
    }

    public void setCenterX(double v) {
        circle.setCenterX(v);
    }

    public void setCenterY(double v) {
        circle.setCenterY(v);
    }
}
