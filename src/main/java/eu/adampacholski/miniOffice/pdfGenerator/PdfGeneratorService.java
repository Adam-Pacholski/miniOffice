package eu.adampacholski.miniOffice.pdfGenerator;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.*;
import eu.adampacholski.miniOffice.invoice.Invoice;
import eu.adampacholski.miniOffice.invoice.InvoiceService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PdfGeneratorService {

    private final InvoiceService invoiceService;

    public PdfGeneratorService(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }


    public PdfPTable export(HttpServletResponse response, Long id) throws IOException {

//        PdfPTable table = null;
        response.setContentType("application/pdf");
        Invoice invoice = invoiceService.getById(id);

        Document document = new Document(PageSize.A4, 36, 36, 65, 36);
        PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
        writer.setPageEvent(new HeaderAndFooterPageEventHelper());

        document.open();

        Font fontInvoiseType = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontInvoiseType.setSize(18);


        Paragraph invoiceTitle = new Paragraph(invoice.getInvoiceType().getType(), fontInvoiseType);
        invoiceTitle.setAlignment(Paragraph.ALIGN_RIGHT);
        document.add(invoiceTitle);

        Font fontCustomer = FontFactory.getFont(FontFactory.HELVETICA);
        fontCustomer.setSize(16);

        Paragraph customerNameParagraph = new Paragraph(invoice.getCustomer().getName(), fontCustomer);
        customerNameParagraph.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(customerNameParagraph);

        fontCustomer.setSize(12);

        Paragraph customerStreetParagraph = new Paragraph(invoice.getCustomer().getStreet() + ",", fontCustomer);
        document.add(customerStreetParagraph);

        Paragraph customerCityParagraph = new Paragraph(invoice.getCustomer().getPostCode() + " " + invoice.getCustomer().getCity(), fontCustomer);
        document.add(customerCityParagraph);

        PdfPTable empty = new PdfPTable(1);
        empty.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        empty.getDefaultCell().setMinimumHeight(70);
        empty.addCell("");
        document.add(empty);

        // info tabela

        PdfPTable box = new PdfPTable(1);
        box.setTotalWidth(PageSize.A4.getWidth() - 50);
        box.getDefaultCell().setBorder(Rectangle.TOP | Rectangle.BOTTOM);
        box.setLockedWidth(true);

        PdfPTable infoTable = new PdfPTable(3);
        infoTable.setTotalWidth(PageSize.A4.getWidth() - 50);
        infoTable.setLockedWidth(true);
        infoTable.setWidths(new int[]{33, 34, 33});

        infoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
//        infoTable.getDefaultCell().setPaddingTop(5);
        infoTable.getDefaultCell().setPaddingBottom(5);

        Font fontTableInfo = FontFactory.getFont(FontFactory.HELVETICA);
        fontTableInfo.setSize(8);

        Font fontTableInfo_2 = FontFactory.getFont(FontFactory.HELVETICA);
        fontTableInfo_2.setSize(12);
        fontTableInfo_2.setColor(Color.RED);

        Font fontTableInfo_3 = FontFactory.getFont(FontFactory.HELVETICA);
        fontTableInfo_3.setSize(12);
        fontTableInfo_3.setColor(Color.BLACK);

        String data = String.valueOf(invoice.getRisedDate().getDayOfMonth() + "." + invoice.getRisedDate().getMonthValue() + "." + invoice.getRisedDate().getYear());

        Paragraph paragraphTableInfo = new Paragraph("Nr rachunku", fontTableInfo);

        infoTable.addCell(paragraphTableInfo);

        paragraphTableInfo = new Paragraph("Data wystawienia", fontTableInfo);
        infoTable.addCell(paragraphTableInfo);

        paragraphTableInfo = new Paragraph("Termin płatności", fontTableInfo);

        infoTable.addCell(paragraphTableInfo);

        paragraphTableInfo = new Paragraph(invoice.getInvoiceNumber(), fontTableInfo_2);
        infoTable.addCell(paragraphTableInfo);


        paragraphTableInfo = new Paragraph(data, fontTableInfo_3);
        infoTable.addCell(paragraphTableInfo);

        data = String.valueOf(invoice.getTerminDate().getDayOfMonth() + "." + invoice.getTerminDate().getMonthValue() + "." + invoice.getTerminDate().getYear());
        paragraphTableInfo = new Paragraph(data, fontTableInfo_3);
        infoTable.addCell(paragraphTableInfo);

        infoTable.addCell(paragraphTableInfo);

        box.addCell(infoTable);

        document.add(box);


        //lista produktów


        //Header
        PdfPTable produktTable = new PdfPTable(7);
        produktTable.setTotalWidth(PageSize.A4.getWidth() - 50);
        produktTable.setLockedWidth(true);
        produktTable.setWidths(new int[]{5, 42,3, 8, 8, 17, 17});
        produktTable.getDefaultCell().setMinimumHeight(25);
        produktTable.getDefaultCell().setBackgroundColor(Color.LIGHT_GRAY);
        produktTable.getDefaultCell().setBorder(Rectangle.BOTTOM);
        produktTable.getDefaultCell().setPaddingLeft(4);
        produktTable.getDefaultCell().setPaddingBottom(4);
        produktTable.getDefaultCell().setPaddingTop(4);

        produktTable.addCell("LP");
        produktTable.addCell("Nazwa produktu");
        produktTable.addCell("");
        produktTable.addCell("Ilość");
        produktTable.addCell("VAT %");
        produktTable.addCell("Netto [zł]");
        produktTable.addCell("Brutto [zł]");

        //produkty
        produktTable.getDefaultCell().setBackgroundColor(Color.WHITE);

        for (int i = 0; i < invoice.getProductLists().size(); i++) {
            produktTable.addCell(String.valueOf(i+1));
            produktTable.addCell(invoice.getProductLists().get(i).getItem().getName());
            produktTable.addCell("");
            produktTable.addCell(String.valueOf(invoice.getProductLists().get(i).getAmount()));
            produktTable.addCell(String.valueOf(invoice.getProductLists().get(i).getTax()));
            produktTable.addCell(String.valueOf(invoice.getProductLists().get(i).getSumNetto()));
            produktTable.addCell(String.valueOf(invoice.getProductLists().get(i).getSumBrutto()));

        }
        document.add(produktTable);

        // suma

        PdfPTable empty_2 = new PdfPTable(1);
        empty_2.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        empty_2.getDefaultCell().setMinimumHeight(20);
        empty_2.addCell("");
        document.add(empty_2);



        PdfPTable table = new PdfPTable(4);

        table.setTotalWidth(PageSize.A4.getWidth() - 50);
        table.setLockedWidth(true);
        table.setWidths(new int[]{50, 10, 20, 20});
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);

        table.addCell("");
        table.addCell("");
        table.addCell("Netto");
        table.addCell("Brutto");
        table.addCell("");
        Font sumaF = FontFactory.getFont(FontFactory.HELVETICA);
        Paragraph sumaP = new Paragraph("Suma:",sumaF);
        sumaP.setAlignment(Paragraph.ALIGN_RIGHT);

        table.addCell(sumaP);

        table.addCell(String.valueOf(invoice.getSumNetto())+" zł");
        table.addCell(String.valueOf(invoice.getSumBrutto())+" zł");



        document.add(table);
        document.close();
        writer.close();
        return table;
    }
}

class HeaderAndFooterPageEventHelper extends PdfPageEventHelper {


    @Override
    public void onEndPage(PdfWriter writer, Document document) {

        /* Footer */


        PdfPTable box = new PdfPTable(1);
        box.setTotalWidth(PageSize.A4.getWidth() - 50);
        box.getDefaultCell().setBorder(Rectangle.TOP);
        box.setLockedWidth(true);

        PdfPTable table = new PdfPTable(3);
        table.setTotalWidth(510);
        table.setWidths(new int[]{33, 34, 33});
        // Magic about default cell - if you add styling to default cell it will apply to all cells except cells added using addCell(PdfPCell) method.
        table.getDefaultCell().setPaddingBottom(5);
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);

        Font fontFactory = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontFactory.setSize(10);
        Font fontFactory2 = FontFactory.getFont(FontFactory.HELVETICA);
        fontFactory.setSize(10);
        Paragraph title = new Paragraph("Adam Pacholski", fontFactory);

        table.addCell(title);

        Paragraph kontakt = new Paragraph("Kontakt", fontFactory);
        table.addCell(kontakt);

        Paragraph bank = new Paragraph("Bank", fontFactory);
        table.addCell(bank);

        title = new Paragraph("Am Bahndamm 10",fontFactory2);
        table.addCell(title);

        kontakt = new Paragraph("+4901783466541",fontFactory2);
        table.addCell(kontakt);

        bank = new Paragraph("Bank: Volksbank Vechta eG", fontFactory2);
        table.addCell(bank);

        title = new Paragraph("26197 Großenkneten",fontFactory2);
        table.addCell(title);

        kontakt = new Paragraph("info@adampacholski.eu",fontFactory2);
        table.addCell(kontakt);

        bank = new Paragraph("DE15 2806 4179 0150 9730 00", fontFactory2);
        table.addCell(bank);

        table.addCell("");
        table.addCell("");
        Paragraph pageNumberText = new Paragraph("Strona " + document.getPageNumber(), new Font(Font.HELVETICA, 10));
        PdfPCell strona = new PdfPCell(pageNumberText);
        strona.setHorizontalAlignment(Element.ALIGN_RIGHT);
        strona.setBorder(Rectangle.NO_BORDER);
        strona.setPaddingTop(10);
        table.addCell(strona);

        box.addCell(table);
        // write the table on PDF
        box.writeSelectedRows(0, -1, 34, 100, writer.getDirectContent());
    }
}
