package model.discountStrategy;

public enum DiscountEnum {
    GROUP("Group", "model.discountStrategy.GroupDiscount"),
    THRESHOLD("Threshold", "model.discountStrategy.ThresholdDiscount"),
    EXPENSIVE("Expensive", "model.discountStrategy.ExpensiveDiscount");

    private final String discountName;
    private final String className;

    DiscountEnum(String discountName, String className) {
        this.discountName = discountName;
        this.className = className;
    }
    public String getDiscountName(){return discountName; }
    public String getClassName(){return className; }
}
