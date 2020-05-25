package model.loadSaveStrategy;

import javafx.collections.ObservableList;
import model.Article;
import model.database.LoadSaveStrategy;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Jonna J.
 */
public class LoadSaveContext {
    private LoadSaveStrategy strategy;

    public LoadSaveContext(){}

    public LoadSaveStrategy getStrategy(){
        return this.strategy;
    }

    public void setStrategy(LoadSaveStrategy strategy){
        this.strategy = strategy;
    }

    public ObservableList<Article> load(){
        return strategy.load();
    }

    public void save(ObservableList<Article> articles){
        strategy.save(articles);
    }

    public static List<String> getStrategyList(){
        List<String> list = new ArrayList<>();
        for(LoadSaveEnum strategy:LoadSaveEnum.values()){
            list.add(strategy.toString());
        }
        return list;
    }
}
