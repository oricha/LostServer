package com.kmuniz.lostserver.util;

import com.kmuniz.lostserver.data.LostItemEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * The FileParser interface defines the contract for parsing different types of files
 * to extract lost item details. Any class implementing this interface should
 * provide specific parsing logic based on the file type.
 */
public interface FileParser {

    /**
     * Parses the provided file and extracts a list of lost items.
     *
     * @param file the file to be parsed, typically uploaded by the user
     * @return a list of LostItemEntity objects extracted from the file
     * @throws IOException if there is an issue while reading or processing the file
     */
    List<LostItemEntity> parse(MultipartFile file) throws IOException;
}