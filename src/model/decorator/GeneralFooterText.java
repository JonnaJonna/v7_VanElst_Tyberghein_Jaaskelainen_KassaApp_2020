package model.decorator;

/**
 * @author Wouter V.E.
 */

public class GeneralFooterText extends InvoiceDecorator{
    Invoice invoice;

    public GeneralFooterText(Invoice invoice){
        this.invoice = invoice;
    }
    @Override
    public String getInvoice() {
        return invoice.getInvoice() + "\n" + "FooterText"; //TODO replace FooterText by code that pulls the proper text from the SettingsPane
    }
}
