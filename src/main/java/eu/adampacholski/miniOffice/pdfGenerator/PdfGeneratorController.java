package eu.adampacholski.miniOffice.pdfGenerator;

import eu.adampacholski.miniOffice.invoice.Invoice;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(path = "api/v1/invoice")
public class PdfGeneratorController {

    @Autowired
    private PdfGeneratorService pdfGeneratorService;

    @GetMapping(path = "/pdf/{id}")
    public void generatePDF(HttpServletResponse response, @PathVariable("id") Long id) throws IOException{
        pdfGeneratorService.export(response, id);
    }

}
