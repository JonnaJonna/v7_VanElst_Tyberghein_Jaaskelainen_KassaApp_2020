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

    private String loadSaveStrategy;
    private String discountStrategy;
    private String discountParams;

    public KassaProperties(){
        try(InputStream fis = new FileInputStream(filename)){
            properties.load(fis);
            //get strategy name from properties file
            this.loadSaveStrategy = properties.getProperty("loadSaveStrategy"); //create the correct strategy class
            this.discountStrategy = properties.getProperty("discountStrategy");
            this.discountParams = properties.getProperty("discountParams");
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
}
