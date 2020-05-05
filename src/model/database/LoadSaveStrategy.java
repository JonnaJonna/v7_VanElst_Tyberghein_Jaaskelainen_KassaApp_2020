package model.database;
import javafx.collections.ObservableList;
import model.Article;

/**
 * @author Jonna J.
 */

public interface LoadSaveStrategy {
    ObservableList<Article> load();
    void save(ObservableList<Article> articles);
}
