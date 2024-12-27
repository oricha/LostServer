package com.kmuniz.lostserver.util;

import com.kmuniz.lostserver.data.LostItemEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PdfParser {

    public static List<LostItemEntity> parseLostItems(MultipartFile file) throws IOException {
        List<LostItemEntity> items = new ArrayList<>();
        // do something with the PDF file here

        return items;
    }
}
