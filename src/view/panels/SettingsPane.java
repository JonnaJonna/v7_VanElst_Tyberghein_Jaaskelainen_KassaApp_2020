package view.panels;


import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.KassaProperties;
import model.LoadSaveContext;
import model.Shop;

import java.util.List;
import java.util.Properties;

import static model.LoadSaveContext.getStrategyList;

/**
 * @author Wouter V E
 * @author Jonna J.
 */
//tab3 of kassaview
public class SettingsPane extends GridPane {
    ToggleGroup tg;

    public SettingsPane(Shop shop) {
        tg = new ToggleGroup();

        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        Label typeLabel = new Label("file type:");
        //TODO set radiobutton value as what is current strategy
        RadioButton excel = new RadioButton("Excel"); //TODO fetch value from context class
        RadioButton text = new RadioButton("Text"); //TODO fetch value from context class
        excel.setToggleGroup(tg);
        text.setToggleGroup(tg);

        Button saveButton = new Button("Save");
        saveButton.setOnAction(this::handleButtonAction);

        this.add(typeLabel, 0, 0);
        this.add(excel, 5, 0);
        this.add(text, 5, 5);
        this.add(saveButton, 10, 10);

    }
    private void handleButtonAction(ActionEvent event) {
        //TODO send notification "saved"
        KassaProperties properties = new KassaProperties();
        RadioButton loadSaveStrategy = (RadioButton)tg.getSelectedToggle();
        if (loadSaveStrategy != null) {
            String s = loadSaveStrategy.getText();
            properties.setLoadSaveStrategy(s);
            System.out.println("Saved");
        }
    }
}
