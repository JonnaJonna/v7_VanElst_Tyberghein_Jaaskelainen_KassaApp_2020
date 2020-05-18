package view.panels;


import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import model.LoadSaveContext;
import model.Shop;

/**
 * @author Wouter V E
 */

public class SettingsPane extends GridPane {

    public SettingsPane(Shop shop) {
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        Label typeLabel = new Label("file type:");

        RadioButton excel = new RadioButton("Excel"); //TODO fetch value from context class
        RadioButton text = new RadioButton("Text"); //TODO fetch value from context class

        this.add(typeLabel, 0, 0);
        this.add(excel, 5, 0);
        this.add(text, 5, 5);

        //TODO save button to properties
    }
}
