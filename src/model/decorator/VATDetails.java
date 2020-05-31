package model.decorator;

import model.shoppingCart.ShoppingCart;

import java.text.DecimalFormat;

/**
 * @author Wouter V.E.
 */

public class VATDetails extends InvoiceDecorator{
    Invoice invoice;
    ShoppingCart cart;
    private static DecimalFormat dec = new DecimalFormat("0.00");

    public VATDetails(Invoice invoice, ShoppingCart cart){
        this.invoice = invoice;
        this.cart = cart;
    }
    @Override
    public String getInvoice() {
        return invoice.getInvoice() + "\nPrice excluding VAT: €" + dec.format((cart.getTotalAfterDiscount()*0.94)) + "\nTotal VAT: €" +
                dec.format((cart.getTotalAfterDiscount()*0.06));
    }
}
