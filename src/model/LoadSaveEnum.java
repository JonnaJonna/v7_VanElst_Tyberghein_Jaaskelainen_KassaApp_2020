package model;

/**
 * @author Jonna J.
 */
public enum LoadSaveEnum {
    TEXT("Text", "model.database.TextLoadSaveStrategy"),
    EXCEL("Excel", "model.database.ExcelLoadSaveStrategy");

    private final String databaseName;
    private final String className;

    LoadSaveEnum(String databaseName, String className) {
        this.databaseName = databaseName;
        this.className = className;
    }
    public String getDatabaseName(){return databaseName; }
    public String getClassName(){return className; }
}
