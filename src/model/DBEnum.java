package model;

/**
 * @author Jonna J.
 */
public enum DBEnum {
    TEXT("Text", "model.database.TextDatabase"),
    EXCEL("Excel", "model.database.ExcelDatabase");

    private final String databaseName;
    private final String className;

    DBEnum(String databaseName, String className) {
        this.databaseName = databaseName;
        this.className = className;
    }
    public String getDatabaseName(){return databaseName; }
    public String getClassName(){return className; }
}
