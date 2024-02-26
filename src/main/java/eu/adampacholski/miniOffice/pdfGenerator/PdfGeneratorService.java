package eu.adampacholski.miniOffice.pdfGenerator;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PdfGeneratorService {
    public PdfPTable export(HttpServletResponse response, Integer id) throws IOException{

        response.setContentType("application/pdf");


        Document document = new Document(PageSize.A4,36, 36, 65, 36);
        PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
        writer.setPageEvent(new HeaderAndFooterPageEventHelper());

        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        Paragraph paragraph = new Paragraph("Informacja o produkcie", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);

        Paragraph paragraph1 = new Paragraph("Po nizej cos tam:", fontParagraph);
        paragraph1.setAlignment(Element.ALIGN_LEFT);
        document.add(paragraph1);


        PdfPTable table;
        PdfPTable tableInCell;
        tableInCell = new PdfPTable(1);
        tableInCell.addCell("test1");
        tableInCell.addCell("test2");
        table = new PdfPTable(3);
        table.addCell(tableInCell);
        table.addCell("heder 2");
        table.addCell("heder 3");

        //Add rows to the table

        document.add(table);
        document.close();
        writer.close();
        return table;
    }
}

class HeaderAndFooterPageEventHelper extends PdfPageEventHelper {

    @Override
    public void onStartPage(PdfWriter writer, Document document) {

        /* Header */
        PdfPTable table = new PdfPTable(3);
        table.setTotalWidth(510);
        table.setWidths(new int[]{38, 36, 36});
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setPaddingBottom(5);
        table.getDefaultCell().setBorder(Rectangle.BOTTOM);

        PdfPCell emptyCell = new PdfPCell(new Paragraph(""));
        emptyCell.setBorder(Rectangle.NO_BORDER);

        // Row#1 having 1 empty cell, 1 title cell and empty cell.
        table.addCell(emptyCell);
        Paragraph title =  new Paragraph("Grogu Inc.", new Font(Font.COURIER, 20, Font.BOLD));
        PdfPCell titleCell = new PdfPCell(title);
        titleCell.setPaddingBottom(10);
        titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        titleCell.setBorder(Rectangle.NO_BORDER);
        table.addCell(titleCell);
        table.addCell(emptyCell);

        //Row#2 having 3 cells
        Font cellFont = new Font(Font.HELVETICA, 8);
        table.addCell(new Paragraph("Phone Number: 888-999-0000", cellFont));
        table.addCell(new Paragraph("Address : 333, Manhattan, New York", cellFont));
        table.addCell(new Paragraph("Website : http://grogu-yoda.com", cellFont));

        // write the table on PDF
        table.writeSelectedRows(0, -1, 34, 828, writer.getDirectContent());
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {

        /* Footer */
        PdfPTable table = new PdfPTable(2);
        table.setTotalWidth(510);
        table.setWidths(new int[]{50,50});
        // Magic about default cell - if you add styling to default cell it will apply to all cells except cells added using addCell(PdfPCell) method.
        table.getDefaultCell().setPaddingBottom(5);
        table.getDefaultCell().setBorder(Rectangle.TOP);

        Paragraph title =  new Paragraph("Grogu Inc.", new Font(Font.HELVETICA, 10));
        PdfPCell titleCell = new PdfPCell(title);
        titleCell.setPaddingTop(4);
        titleCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        titleCell.setBorder(Rectangle.TOP);
        table.addCell(titleCell);

        Paragraph pageNumberText =  new Paragraph("Page "+document.getPageNumber(), new Font(Font.HELVETICA, 10));
        PdfPCell pageNumberCell = new PdfPCell(pageNumberText);
        pageNumberCell.setPaddingTop(4);
        pageNumberCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        pageNumberCell.setBorder(Rectangle.TOP);
        table.addCell(pageNumberCell);

        // write the table on PDF
        table.writeSelectedRows(0, -1, 34, 36, writer.getDirectContent());
    }
}
