package model.database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Article;

import java.util.ArrayList;

/** Excel adapter class for ExcelPlugin.jar
 */

public class ExcelLoadSaveStrategy implements LoadSaveStrategy {
    private static ExcelLoadSaveStrategy uniqueInstance;

    private ExcelLoadSaveStrategy(){}

    public static ExcelLoadSaveStrategy getInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new ExcelLoadSaveStrategy();
        }
        return uniqueInstance;
    }

    @Override
    public ObservableList<Article> load() {
        //This is just for testing that choosing strategy works, removed when starting to work with excelplugin
        System.out.print("This is excel load strategy");
        ObservableList<Article> test;
        test = FXCollections.observableArrayList(new ArrayList<Article>());
        Article article = new Article(12, "Coffee", "Drinks",4.5,5);
        test.add(article);
        return test;
    }

    @Override
    public void save(ObservableList<Article> articles) {
        System.out.print("This is excel save strategy");
    }
}
