package model;

import model.database.LoadSaveStrategy;

public class LoadSaveFactory {
    public static LoadSaveStrategy createLoadSaveStrategy(String strategyName){
        LoadSaveEnum dbEnum = LoadSaveEnum.valueOf(strategyName);
        String className = dbEnum.getClassName();
        LoadSaveStrategy strategy = null;
        try{
            Class dbclass = Class.forName(className);
            Object dbobject = dbclass.getDeclaredConstructor().newInstance();
            //newInstance deprecated -> check what to use instead
            strategy = (LoadSaveStrategy) dbobject;
        }catch (Exception e){
            System.out.println(e);
        }
        return strategy;
    }
}
