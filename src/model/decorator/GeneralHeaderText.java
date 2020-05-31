package model.decorator;

/**
 * @author Wouter V.E.
 */

public class GeneralHeaderText extends InvoiceDecorator{
    Invoice invoice;

    public GeneralHeaderText(Invoice invoice){
        this.invoice = invoice;
    }
    @Override
    public String getInvoice() {
        return "HeaderText" + "\n" + invoice.getInvoice(); //TODO replace HeaderText by code that pulls proper text from SettingsPane
    }
}
