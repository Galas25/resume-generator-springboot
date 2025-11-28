package com.cosc75.demo.Services;

import com.cosc75.demo.Models.Education;
import com.cosc75.demo.Models.Experience;
import com.cosc75.demo.Models.PersonalInfo;
import com.cosc75.demo.Models.ProfessionalSummary;
import com.lowagie.text.*;
import com.lowagie.text.Font; // Explicit import to avoid confusion
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;
import org.springframework.stereotype.Service;

import java.awt.Color; // OpenPDF uses standard Java AWT Colors
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class ResumeService {

    // --- 1. FIXED FONTS (Using 'new Font' instead of 'FontFactory') ---

    // Title: Helvetica, Size 24, Bold, Dark Blue
    private static final Font TITLE_FONT = new Font(Font.HELVETICA, 24, Font.BOLD, new Color(44, 62, 80));

    // Subtitle: Helvetica, Size 12, Normal, Gray
    private static final Font SUBTITLE_FONT = new Font(Font.HELVETICA, 12, Font.NORMAL, Color.GRAY);

    // Section Header: Helvetica, Size 14, Bold, White
    private static final Font SECTION_HEADER_FONT = new Font(Font.HELVETICA, 14, Font.BOLD, Color.WHITE);

    // Body: Helvetica, Size 11, Normal, Black
    private static final Font BODY_FONT = new Font(Font.HELVETICA, 11, Font.NORMAL, Color.BLACK);

    // Bold Body: Helvetica, Size 11, Bold, Black
    private static final Font BOLD_BODY_FONT = new Font(Font.HELVETICA, 11, Font.BOLD, Color.BLACK);

    public byte[] generateResumePdf(PersonalInfo personalInfo,
                                    ProfessionalSummary summary,
                                    List<Experience> experienceList,
                                    Education education,
                                    List<String> skills) {

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            // A4 Margins: Left, Right, Top, Bottom
            Document document = new Document(PageSize.A4, 40, 40, 40, 40);
            PdfWriter.getInstance(document, out);
            document.open();

            // --- HEADER SECTION ---
            addHeader(document, personalInfo);
            addEmptyLine(document, 1);

            // --- SUMMARY SECTION ---
            if (summary != null) {
                addSectionTitle(document, "PROFESSIONAL SUMMARY");
                document.add(new Paragraph(summary.getSummary(), BODY_FONT));
                addEmptyLine(document, 1);
            }

            // --- EXPERIENCE SECTION ---
            if (experienceList != null && !experienceList.isEmpty()) {
                addSectionTitle(document, "WORK EXPERIENCE");
                for (Experience exp : experienceList) {
                    Paragraph expGraph = new Paragraph("• " + exp.getSummary(), BODY_FONT);
                    expGraph.setSpacingAfter(5f);
                    document.add(expGraph);
                }
                addEmptyLine(document, 1);
            }

            // --- EDUCATION SECTION ---
            if (education != null) {
                addSectionTitle(document, "EDUCATION");
                PdfPTable table = new PdfPTable(2);
                table.setWidthPercentage(100);
                table.setWidths(new float[]{1, 3});

                addEducationRow(table, "Tertiary", education.getTertiaryEducation());
                addEducationRow(table, "Secondary", education.getSecondaryEducation());
                addEducationRow(table, "Primary", education.getPrimaryEducation());

                document.add(table);
                addEmptyLine(document, 1);
            }

            // --- SKILLS SECTION ---
            if (skills != null && !skills.isEmpty()) {
                addSectionTitle(document, "TECHNICAL SKILLS");
                String skillString = String.join(", ", skills);
                document.add(new Paragraph(skillString, BODY_FONT));
            }

            document.close();
            return out.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // --- HELPER METHODS ---

    private void addHeader(Document document, PersonalInfo info) throws DocumentException {
        if (info == null) return;

        // Name
        String fullName = (info.getName() + " " + (info.getSuffix() != null ? info.getSuffix() : "")).trim();
        Paragraph name = new Paragraph(fullName.toUpperCase(), TITLE_FONT);
        name.setAlignment(Element.ALIGN_CENTER);
        document.add(name);

        // Contact
        String contactText = info.getEmail() + "  |  " + info.getPhone();
        Paragraph contact = new Paragraph(contactText, SUBTITLE_FONT);
        contact.setAlignment(Element.ALIGN_CENTER);
        contact.setSpacingAfter(10f); // Space before line
        document.add(contact);

        // Line Separator
        LineSeparator ls = new LineSeparator();
        ls.setLineColor(Color.LIGHT_GRAY);
        document.add(new Chunk(ls));
    }

    private void addSectionTitle(Document document, String title) throws DocumentException {
        // Table for the colored bar
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);

        PdfPCell cell = new PdfPCell(new Phrase(title, SECTION_HEADER_FONT));
        cell.setBackgroundColor(new Color(44, 62, 80)); // Dark Blue Background
        cell.setPaddingBottom(6);
        cell.setPaddingTop(4);
        cell.setPaddingLeft(10);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

        table.addCell(cell);
        document.add(table);

        // Small space after the bar
        document.add(new Paragraph(" ", new Font(Font.HELVETICA, 4)));
    }

    private void addEducationRow(PdfPTable table, String label, String value) {
        if (value == null) return;

        PdfPCell labelCell = new PdfPCell(new Phrase(label, BOLD_BODY_FONT));
        labelCell.setBorder(Rectangle.NO_BORDER);
        labelCell.setPaddingBottom(5);

        PdfPCell valueCell = new PdfPCell(new Phrase(value, BODY_FONT));
        valueCell.setBorder(Rectangle.NO_BORDER);
        valueCell.setPaddingBottom(5);

        table.addCell(labelCell);
        table.addCell(valueCell);
    }

    private void addEmptyLine(Document document, int number) throws DocumentException {
        for (int i = 0; i < number; i++) {
            document.add(new Paragraph(" "));
        }
    }
}