package model.decorator;

import model.shoppingCart.ShoppingCart;

import java.text.DecimalFormat;

/**
 * @author Wouter V.E.
 */

public class DiscountDetails extends InvoiceDecorator{
    Invoice invoice;
    ShoppingCart cart;
    private static DecimalFormat dec = new DecimalFormat("0.00");

    public DiscountDetails(Invoice invoice, ShoppingCart cart){
        this.invoice = invoice;
        this.cart = cart;
    }
    @Override
    public String getInvoice() {
        return invoice.getInvoice() +  "\nPrice before discount: €" + cart.getTotalPrice() + "\nTotal discount: €" +
                dec.format((cart.getTotalPrice()-cart.getTotalAfterDiscount()));
    }
}
