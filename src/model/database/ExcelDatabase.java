package model.database;
import javafx.collections.ObservableList;
import model.Article;

public class ExcelDatabase implements ArticleDatabase{
    @Override
    public ObservableList<Article> load() {
        return null;
    }

    @Override
    public void save(ObservableList<Article> articles) {

    }
}
