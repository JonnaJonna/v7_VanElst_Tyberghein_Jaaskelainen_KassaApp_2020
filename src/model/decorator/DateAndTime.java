package model.decorator;
import java.time.LocalDateTime;

/**
 * @author Wouter V.E.
 */

public class DateAndTime extends InvoiceDecorator{
    Invoice invoice;
    LocalDateTime timestamp;

    public DateAndTime(Invoice invoice){
        this.invoice = invoice;
    }
    @Override
    public String getInvoice() {
        timestamp = LocalDateTime.now();
        return "Date of sale: " + timestamp.getDayOfMonth() +  "/" + timestamp.getMonth() + "/" + timestamp.getYear() + "\nTime of sale: " + timestamp.getHour() + ":" + timestamp.getMinute() + ":" + timestamp.getSecond() + "\n" + invoice.getInvoice();
    }
}
