package model.discountStrategy;

/**
 * @author Jonna J.
 * Discount calculated only for items belonging to a certain group
 */
public class GroupDiscount extends DiscountStrategy {

    private String groupName;

    public GroupDiscount(String args) {
        super(args);
        String[] values = args.split("/");
        setGroupName(values[1]);
    }

    @Override
    public void calculateDiscount() {
        System.out.println("Calculating group discount for " + this.getPercentage() + " group " + getGroupName());
    }

    public void setGroupName(String groupName){
        this.groupName = groupName;
    }

    public String getGroupName(){
        return this.groupName;
    }

    public void setValues(double percentage, String groupName){
        setPercentage(percentage);
        setGroupName(groupName);
    }
}
