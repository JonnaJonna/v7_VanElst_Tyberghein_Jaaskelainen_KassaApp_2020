package view.panels;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.KassaProperties;
import model.Shop;
import model.discountStrategy.DiscountContext;
import model.loadSaveStrategy.LoadSaveContext;

import javax.xml.soap.Text;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Wouter V E
 * @author Jonna J.
 */
//tab3 of kassaview
public class SettingsPane extends GridPane {
    protected ToggleGroup tg, rg;
    protected Label percentageLabel, paramsLabel;
    protected TextField percentageField, paramsField;
    protected KassaProperties kassaProperties = new KassaProperties();

    public SettingsPane(Shop shop) {
        tg = new ToggleGroup();
        rg = new ToggleGroup(); //create the RadioGroup

        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(20);

        Label discountLabel = new Label("Discount type");
        Label discountParams= new Label("Discount parameters");
        Label typeLabel = new Label("File type:");

        percentageLabel = new Label("Percentage: ");
        percentageField = new TextField();
        this.add(percentageLabel, 2,1);
        this.add(percentageField, 3,1);

        paramsLabel = new Label("");    //params for discount
        paramsField = new TextField();
        paramsField.setDisable(true);
        this.add(paramsLabel, 2,2);
        this.add(paramsField, 3,2);

        createRadioButtons(shop);
        createDiscountParams();

        Button saveButton = new Button("Save");
        saveButton.setOnAction(this::handleButtonAction);

        this.add(typeLabel, 0, 0);
        this.add(discountLabel, 1, 0);
        this.add(discountParams, 2, 0);

        this.add(saveButton, 5, 5);
    }
    private void handleButtonAction(ActionEvent event) {
        //TODO send notification "saved"
        RadioButton loadSaveStrategy = (RadioButton)tg.getSelectedToggle();
        RadioButton discountStrategy = (RadioButton)rg.getSelectedToggle();
        if (loadSaveStrategy != null) {
            kassaProperties.setLoadSaveStrategy(loadSaveStrategy.getText().toUpperCase());
        }
        if (discountStrategy != null) {
            kassaProperties.setDiscountStrategy(discountStrategy.getText().toUpperCase());
            if (percentageField != null && !percentageField.getText().equals("")) {
                kassaProperties.setDiscountParams(percentageField.getText() + "/" + paramsField.getText());
            }
        }
        kassaProperties.setKassaProperties();
    }
    private void createRadioButtons(Shop shop){
        List<String> discountList = shop.getDiscountContext().getStrategyList();
        List<String> fileList = shop.getLoadSaveContext().getStrategyList();

        for (int i = 0; i < discountList.size(); i++){
            RadioButton rb = new RadioButton(discountList.get(i).toString());
            rb.setToggleGroup(rg);
            this.add(rb, 1, i + 1);
        }
        for (int i = 0; i < fileList.size(); i++){
            RadioButton rb = new RadioButton(fileList.get(i).toString());
            rb.setToggleGroup(tg);
            this.add(rb, 0, i + 1);
        }
    }
    private void createDiscountParams(){
        //check which discountButton is chosen and show the change label based on that
        //percentage is common for all
        rg.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                paramsField.clear();
                if (rg.getSelectedToggle() != null) {
                    RadioButton rb = (RadioButton)rg.getSelectedToggle();
                    if(rb.getText().equals("GROUP")){
                        paramsField.setDisable(false);
                        paramsLabel.setText("Group name");
                    }
                    if(rb.getText().equals("THRESHOLD")){
                        paramsField.setDisable(false);
                        paramsLabel.setText("Threshold value");
                    }
                    if(rb.getText().equals("EXPENSIVE")){
                        paramsLabel.setText("");
                        paramsField.setDisable(true);
                    }
            }
        }});
    }
}
