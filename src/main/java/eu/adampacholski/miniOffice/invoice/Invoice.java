package eu.adampacholski.miniOffice.invoice;

import eu.adampacholski.miniOffice.customer.Customer;
import eu.adampacholski.miniOffice.invoice.invoiceStatus.InvoiceStatus;

import java.time.LocalDate;

public class Invoice {
    private Long id;
    private String invoiceNumber;
    private LocalDate risedDate;
    private LocalDate terminDate;
    private LocalDate paidDate;
    private String comments;
    private Integer tax;
    private Integer discount;

    private Customer customer;
    private InvoiceStatus invoiceStatus;



}
