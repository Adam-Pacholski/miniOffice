package eu.adampacholski.miniOffice.pdfGenerator;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PdfGeneratorService {
    public PdfPTable export(HttpServletResponse response) throws IOException{

        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDataTime = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Kupa"+currentDataTime + ".pdf";

        response.setHeader(headerKey, headerValue);

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

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
        table = new PdfPTable(3);
        table.addCell("heder 1");
        table.addCell("heder 2");
        table.addCell("heder 3");

        for (int i = 0; i < 1000; i++) {
            table.addCell("Col " + i);
        }
        //Add rows to the table

        document.add(table);
        document.close();
        return table;
    }
}
