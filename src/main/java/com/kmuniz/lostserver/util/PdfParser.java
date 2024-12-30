package com.kmuniz.lostserver.util;

import com.kmuniz.lostserver.data.LostItemEntity;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The PdfParser class implements the FileParser interface and provides the
 * functionality to parse PDF files to extract lost item details. It uses Apache PDFBox
 * to load and process the PDF document and extract text data.
 */
public class PdfParser implements FileParser {

    /**
     * Parses the provided PDF file and extracts a list of lost items from it.
     *
     * @param file the PDF file containing the lost item data
     * @return a list of LostItemEntity objects representing the lost items
     * @throws IOException if there is an error while reading or processing the PDF file
     */
    @Override
    public List<LostItemEntity> parse(MultipartFile file) throws IOException {
        List<LostItemEntity> items = new ArrayList<>();

        // Load the PDF document
        try (PDDocument document = Loader.loadPDF(file.getInputStream().readAllBytes())) {
            // Extract text using PDFTextStripper
            String text = new PDFTextStripper().getText(document);

            // Process the text line by line
            String[] lines = text.split("\n");
            for (int i = 0; i < lines.length; i += 3) {
                LostItemEntity item = new LostItemEntity();
                item.setItemName(lines[i].split(":")[1].trim());
                item.setQuantity(Integer.parseInt(lines[i + 1].split(":")[1].trim()));
                item.setPlace(lines[i + 2].split(":")[1].trim());
                items.add(item);
            }
        }

        return items;
    }
}
