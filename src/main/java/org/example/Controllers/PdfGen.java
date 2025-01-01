package org.example.Controllers;

import org.example.Dao.MedicineItem;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.HorizontalAlignment;

import javafx.collections.ObservableList;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PdfGen {

    public static void generatePDF(ObservableList<MedicineItem> items, int totalAmount) {
        try {
            // Get the Downloads folder path
            String downloadsPath = System.getProperty("user.home") + File.separator + "Downloads";
            String fileName = "PurchaseReceipt_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".pdf";
            String filePath = downloadsPath + File.separator + fileName;

            // Create a PDF writer and document
            PdfWriter writer = new PdfWriter(filePath);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Add a logo (update the path to your logo)
            String logoPath = System.getProperty("user.dir") + "/src/main/resources/logo.png"; // Example path
            ImageData imageData = ImageDataFactory.create(logoPath);
            Image logo = new Image(imageData);
            logo.setWidth(100);
            logo.setHeight(100);
            logo.setHorizontalAlignment(HorizontalAlignment.CENTER);
            document.add(logo);

            // Add title and date
            document.add(new Paragraph("Pharmacy Sidi Abbad").setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(18));
            document.add(new Paragraph("Date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .setTextAlignment(TextAlignment.RIGHT).setFontSize(12));

            // Add a table for the items
            Table table = new Table(new float[]{2, 4, 2, 2, 3}); // Column widths
            table.setWidth(100f); // Set table width relative to the page

            // Add table headers
            table.addHeaderCell(new Cell().add(new Paragraph("ID")).setBold().setTextAlignment(TextAlignment.CENTER));
            table.addHeaderCell(new Cell().add(new Paragraph("Medicine")).setBold().setTextAlignment(TextAlignment.CENTER));
            table.addHeaderCell(new Cell().add(new Paragraph("Price/Unit")).setBold().setTextAlignment(TextAlignment.CENTER));
            table.addHeaderCell(new Cell().add(new Paragraph("Quantity")).setBold().setTextAlignment(TextAlignment.CENTER));
            table.addHeaderCell(new Cell().add(new Paragraph("Total")).setBold().setTextAlignment(TextAlignment.CENTER));

            // Add table rows
            for (MedicineItem item : items) {
                table.addCell(new Cell().add(new Paragraph(String.valueOf(item.getMedicineID()))).setTextAlignment(TextAlignment.CENTER));
                table.addCell(new Cell().add(new Paragraph(item.getName())).setTextAlignment(TextAlignment.LEFT));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(item.getPricePerUnit()))).setTextAlignment(TextAlignment.RIGHT));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(item.getNoOfUnits()))).setTextAlignment(TextAlignment.RIGHT));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(item.getTotalPrice()))).setTextAlignment(TextAlignment.RIGHT));
            }

            document.add(table);

            // Add total amount at the end
            document.add(new Paragraph("Total Amount: " + totalAmount + " MAD")
                    .setBold().setTextAlignment(TextAlignment.RIGHT).setFontSize(14));

            // Close the document
            document.close();

            System.out.println("PDF generated successfully at: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
