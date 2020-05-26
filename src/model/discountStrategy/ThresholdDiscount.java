package model.discountStrategy;

/**
 * @author Jonna J.
 * Discount calculate for whole shopping cart that has value more than thresholdValue
 */
public class ThresholdDiscount extends DiscountStrategy {

    private double thresholdValue = 0;

    public ThresholdDiscount(String args) {
        super(args);
        //setThresholdValue(Double.parseDouble(thresholdValue));
        String[] values = args.split("/");
        setThresholdValue(Double.parseDouble(values[1]));
    }

    @Override
    public void calculateDiscount() {
        System.out.println("Calculating group discount for " + this.getPercentage() + " basket over " + getThresholdValue());
    }

    public void setThresholdValue(double thresholdValue) {
        this.thresholdValue = thresholdValue;
    }

    public double getThresholdValue(){
        return this.thresholdValue;
    }

    public void setValues(double percentage, double thresholdValue){
        setPercentage(percentage);
        setThresholdValue(thresholdValue);
    }
}
