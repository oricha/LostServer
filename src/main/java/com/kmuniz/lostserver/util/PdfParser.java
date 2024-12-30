package com.kmuniz.lostserver.util;

import com.kmuniz.lostserver.data.LostItemEntity;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PdfParser implements FileParser {

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
