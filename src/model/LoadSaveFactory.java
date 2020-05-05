package model;

import model.database.LoadSaveStrategy;

import java.lang.reflect.Method;

public class LoadSaveFactory {
    public static LoadSaveStrategy createLoadSaveStrategy(String strategyName){
        LoadSaveEnum dbEnum = LoadSaveEnum.valueOf(strategyName);
        String className = dbEnum.getClassName();
        LoadSaveStrategy strategy = null;
        try{
            Class strategyClass = Class.forName(className);
            Method method = strategyClass.getMethod("getInstance");
            Object dbobject = method.invoke(strategyClass);
            strategy = (LoadSaveStrategy) dbobject;
        }catch (Exception e){
            System.out.println(e);
        }
        return strategy;
    }
}
