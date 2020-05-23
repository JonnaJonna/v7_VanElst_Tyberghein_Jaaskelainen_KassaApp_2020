package model;

import model.database.LoadSaveStrategy;

import java.io.*;
import java.util.Properties;

public class KassaProperties {
    private File filename = new File("src/files/config.properties");
    private Properties properties = new Properties();

    public LoadSaveContext getLoadSaveStrategy() {
        LoadSaveContext loadSaveContext = new LoadSaveContext();
        LoadSaveStrategy loadSaveStrategy;
        try(InputStream fis = new FileInputStream(filename)){
            properties.load(fis);
            //get strategy name from properties file
            loadSaveStrategy = LoadSaveFactory.createLoadSaveStrategy(properties.getProperty("strategy")); //create the correct strategy class
            loadSaveContext.setStrategy(loadSaveStrategy);  //set the correct application strategy
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return loadSaveContext;
    }

    public void setLoadSaveStrategy(String strategy) {
        try(OutputStream fos = new FileOutputStream(filename)){
            properties.setProperty("strategy", strategy.toUpperCase());
            properties.store(fos, null);
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
