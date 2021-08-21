package com.cbapps.gamo.javafx;

import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TextObject extends GameObjectBase {
	private final Label label;
	private final Group group;

	public TextObject() {
		this(new Group());
	}

	private TextObject(Group group) {
		super(group);

		this.group = group;
		this.label = new Label();
		label.setFont(Font.font("Monospaced"));
		label.setTextFill(Color.WHITE);
		label.setBackground(new Background(new BackgroundFill(new Color(0, 0, 0, 0.3), null, null)));
		label.setPadding(new Insets(5));

		this.group.getChildren().add(label);
	}

	public void setCentered(boolean value) {
		if (value) {
			this.label.layoutXProperty().bind(label.widthProperty().multiply(-0.5));
			this.label.layoutYProperty().bind(label.heightProperty().multiply(-0.5));
		} else {
			this.label.layoutXProperty().unbind();
			this.label.layoutYProperty().unbind();
		}
	}

	public void setFontSize(int size) {
		this.label.setFont(Font.font(this.label.getFont().getFamily(), size));
	}

	public void setText(String value) {
		this.label.setText(value);
	}

	public StringProperty textProperty() {
		return label.textProperty();
	}
}
