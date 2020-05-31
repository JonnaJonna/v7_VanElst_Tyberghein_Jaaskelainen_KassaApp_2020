package model.discountStrategy;

import com.sun.xml.internal.fastinfoset.util.StringArray;

import java.lang.reflect.Constructor;

public class DiscountFactory {
    public static DiscountStrategy createDiscount(String discountName, String params){
        DiscountEnum discountEnum = DiscountEnum.valueOf(discountName);
        String className = discountEnum.getClassName();
        DiscountStrategy discountStrategy = null;
        try{
            Class strategyClass = Class.forName(className);
            Constructor ctor = strategyClass.getDeclaredConstructor(String.class);
            ctor.setAccessible(true);
            discountStrategy = (DiscountStrategy)ctor.newInstance(params);
        }catch (Exception e){
            System.out.println(e);
        }
        return discountStrategy;
    }
}
