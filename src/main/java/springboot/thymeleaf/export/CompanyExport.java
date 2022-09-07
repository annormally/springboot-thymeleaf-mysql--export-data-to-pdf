package springboot.thymeleaf.export;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import springboot.thymeleaf.entity.CompanyEntity;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class CompanyExport {

    private final List<CompanyEntity> companyList;

    public CompanyExport(List<CompanyEntity> companyList) {
        this.companyList = companyList;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.COURIER);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Company Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Company Email", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Company Phone", font));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table) {
        for (CompanyEntity companyEntity : companyList) {
            table.addCell(String.valueOf(companyEntity.getCompanyName()));
            table.addCell(String.valueOf(companyEntity.getCompanyEmail()));
            table.addCell(String.valueOf(companyEntity.getCompanyPhone()));
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER_BOLD);
        font.setSize(16);
        font.setColor(Color.ORANGE);

        Paragraph paragraph = new Paragraph("Company Fields", font);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(paragraph);

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100f);
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);
        document.close();
    }
}
