package view.panels;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import jxl.write.DateTime;
import model.KassaProperties;
import model.Shop;
import model.discountStrategy.DiscountContext;
import model.loadSaveStrategy.LoadSaveContext;

import javax.xml.soap.Text;
import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Wouter V E
 * @author Jonna J.
 */
//tab3 of kassaview
public class SettingsPane extends GridPane {
    protected ToggleGroup tg, rg, gt, gc;
    protected Label percentageLabel, paramsLabel;
    protected TextField percentageField, paramsField;
    protected KassaProperties kassaProperties = new KassaProperties();

    public SettingsPane(Shop shop) {
        tg = new ToggleGroup();
        rg = new ToggleGroup(); //create the RadioGroup

        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(20);

        Label discountLabel = new Label("Discount type:");
        Label discountParams= new Label("Discount parameters:");
        Label typeLabel = new Label("File type:");
        Label invoiceHeaderLabel = new Label("Invoice header options:");
        Label invoiceFooterLabel = new Label("Invoice footer options:");

        percentageLabel = new Label("Percentage: ");
        percentageField = new TextField();
        this.add(percentageLabel, 1,5);
        this.add(percentageField, 2,5);

        paramsLabel = new Label("");    //params for discount
        paramsField = new TextField();
        paramsField.setDisable(true);
        this.add(paramsLabel, 1,6);
        this.add(paramsField, 2,6);

        createRadioButtons(shop);
        createDiscountParams();

        Button saveButton = new Button("Save");
        saveButton.setOnAction(this::handleButtonAction);

        this.add(typeLabel, 0, 0);
        this.add(discountLabel, 0, 2);
        this.add(discountParams, 0, 5);
        this.add(invoiceHeaderLabel, 0, 7);
        this.add(invoiceFooterLabel, 0, 9);
        TextField generalMessageText = new TextField();
        generalMessageText.setDisable(true);
        RadioButton dateTime = new RadioButton("DATE + TIME");
        RadioButton totalPrice = new RadioButton("NON-DISCOUNTED PRICE");
        RadioButton priceVAT = new RadioButton("SHOW VAT");
        TextField generalClosureText = new TextField();
        generalClosureText.setDisable(true);

        this.add(generalMessageText, 2, 7);
        this.add(dateTime, 1, 8);
        this.add(totalPrice, 1, 9);
        this.add(priceVAT, 1, 10);
        this.add(generalClosureText, 2, 11);

        this.add(saveButton, 3, 15);
        saveButton.setTranslateX(75);
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
        List<String> openingMessage = new ArrayList<>();
        List<String> closureMessage = new ArrayList<>();


        for (int i = 0; i < discountList.size(); i++){
            RadioButton rb = new RadioButton(discountList.get(i).toString());
            rb.setToggleGroup(rg);
            this.add(rb, 1, i + 2);
        }
        for (int i = 0; i < fileList.size(); i++){
            RadioButton rb = new RadioButton(fileList.get(i).toString());
            rb.setToggleGroup(tg);
            this.add(rb, 1, i);
        }
        openingMessage.add("OPENING MESSAGE:");
        openingMessage.add("NONE");
        for (int i = 0; i < openingMessage.size(); i++){
            RadioButton rb = new RadioButton(openingMessage.get(i));
            rb.setToggleGroup(gt);
            this.add(rb, (i + 1)*2 - 1, 7);
        }
        closureMessage.add("CLOSURE MESSAGE:");
        closureMessage.add("NONE");
        for (int i = 0; i < closureMessage.size(); i++){
            RadioButton rb = new RadioButton(closureMessage.get(i));
            rb.setToggleGroup(gc);
            this.add(rb, (i + 1)*2 - 1, 11);
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
