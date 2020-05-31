package model.decorator;

import model.KassaProperties;

/**
 * @author Wouter V.E.
 */

public class GeneralFooterText extends InvoiceDecorator{
    Invoice invoice;
    KassaProperties properties;

    public GeneralFooterText(Invoice invoice, KassaProperties properties){
        this.invoice = invoice;
        this.properties = properties;
    }
    @Override
    public String getInvoice() {
        return invoice.getInvoice() + "\n" + properties.getFooterText();
    }
}
