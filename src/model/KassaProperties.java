package model;

import model.database.LoadSaveStrategy;
import model.discountStrategy.DiscountContext;
import model.discountStrategy.DiscountFactory;
import model.discountStrategy.DiscountStrategy;
import model.loadSaveStrategy.LoadSaveContext;
import model.loadSaveStrategy.LoadSaveFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Properties;

/**
 * @author Jonna J.
 */
public class KassaProperties {
    private File filename = new File("src/files/config.properties");
    private Properties properties = new Properties();
    //Options for filetype
    private String loadSaveStrategy;
    //Options for discount
    private String discountStrategy;
    private String discountParams;
    //Options for ticket
    private boolean needsHeaderText;
    private boolean needsDateAndTime;
    private boolean needsDiscountDetails;
    private boolean needsVATDetails;
    private boolean needsFooterText;
    private String headerText;
    private String footerText;

    public KassaProperties(){
        try(InputStream fis = new FileInputStream(filename)){
            properties.load(fis);
            //get strategy name from properties file
            this.loadSaveStrategy = properties.getProperty("loadSaveStrategy"); //create the correct strategy class
            this.discountStrategy = properties.getProperty("discountStrategy");
            this.discountParams = properties.getProperty("discountParams");
            this.needsHeaderText = Boolean.parseBoolean(properties.getProperty("needsHeaderText"));
            this.needsDateAndTime = Boolean.parseBoolean(properties.getProperty("needsDateAndTime"));
            this.needsDiscountDetails = Boolean.parseBoolean(properties.getProperty("needsDiscountDetails"));
            this.needsVATDetails = Boolean.parseBoolean(properties.getProperty("needsVATDetails"));
            this.needsFooterText = Boolean.parseBoolean(properties.getProperty("needsFooterText"));
            this.headerText = properties.getProperty("headerText");
            this.footerText = properties.getProperty("footerText");
            fis.close();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public LoadSaveContext getLoadSaveStrategyContext() {
        LoadSaveContext loadSaveContext = new LoadSaveContext();
        LoadSaveStrategy loadSaveStrategy;
        loadSaveStrategy = LoadSaveFactory.createLoadSaveStrategy(getLoadSaveStrategy()); //create the correct strategy class
        loadSaveContext.setStrategy(loadSaveStrategy);  //set the correct application strategy
        return loadSaveContext;
    }

    public DiscountContext getDiscountStrategyContext() {
        DiscountContext discountContext = new DiscountContext();
        DiscountStrategy discountStrategy;
        discountStrategy = DiscountFactory.createDiscount(getDiscountStrategy(),
                getDiscountParams()); //create the correct strategy class
        discountContext.setStrategy(discountStrategy);  //set the correct application strategy
        return discountContext;
    }

    public void setKassaProperties() {
        //map all the choices to properties
        try(OutputStream fos = new FileOutputStream(filename)){
            properties.setProperty("loadSaveStrategy", getLoadSaveStrategy());
            properties.setProperty("discountStrategy", getDiscountStrategy());
            properties.setProperty("discountParams", getDiscountParams());
            properties.setProperty("needsHeaderText", String.valueOf(getNeedsHeaderText()));
            properties.setProperty("needsDateAndTime", String.valueOf(getNeedsDateAndTime()));
            properties.setProperty("needsDiscountDetails", String.valueOf(getNeedsDiscountDetails()));
            properties.setProperty("needsVATDetails", String.valueOf(getNeedsVATDetails()));
            properties.setProperty("needsFooterText", String.valueOf(getNeedsFooterText()));
            properties.setProperty("headerText", getHeaderText());
            properties.setProperty("footerText", getFooterText());
            properties.store(fos, null);
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getDiscountStrategy(){
        return discountStrategy;
    }

    public void setDiscountStrategy(String discountStrategy){
        this.discountStrategy = discountStrategy;
    }

    public String getLoadSaveStrategy() {
        return loadSaveStrategy;
    }

    public void setLoadSaveStrategy(String strategy) {
        this.loadSaveStrategy = strategy;
    }

    public String getDiscountParams() {
        return discountParams;
    }

    public void setDiscountParams(String discountParams) {
        this.discountParams = discountParams;
    }

    public boolean getNeedsHeaderText() {
        return needsHeaderText;
    }

    public boolean getNeedsDateAndTime() {
        return needsDateAndTime;
    }

    public boolean getNeedsDiscountDetails() {
        return needsDiscountDetails;
    }

    public boolean getNeedsVATDetails() {
        return needsVATDetails;
    }

    public boolean getNeedsFooterText() {
        return needsFooterText;
    }

    public String getHeaderText() {
        return headerText;
    }

    public String getFooterText() {
        return footerText;
    }
    public void setNeedsHeaderText(boolean needsHeaderText){
        this.needsHeaderText = needsHeaderText;
    }

    public void setNeedsDateAndTime(boolean needsDateAndTime) {
        this.needsDateAndTime = needsDateAndTime;
    }

    public void setNeedsDiscountDetails(boolean needsDiscountDetails) {
        this.needsDiscountDetails = needsDiscountDetails;
    }

    public void setNeedsVATDetails(boolean needsVATDetails) {
        this.needsVATDetails = needsVATDetails;
    }

    public void setNeedsFooterText(boolean needsFooterText) {
        this.needsFooterText = needsFooterText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }

    public void setFooterText(String footerText) {
        this.footerText = footerText;
    }

}
